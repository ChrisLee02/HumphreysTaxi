package com.example.demo.service.implement;

import com.example.demo.dto.request.board.PostBoardRequestDto;
import com.example.demo.dto.request.board.PostCommentRequestDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.dto.response.board.*;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final BoardListViewRepository boardListViewRepository;
    private final BoardViewRepository boardViewRepository;
    private final CommentRepository commentRepository;
    private final CommentViewRepository commentViewRepository;
    private final JoiningUserRepository joiningUserRepository;
    private final UserRepository userRepository;

    @Value("${penaltyInMin}")
    private int penaltyInMin;

    // todo: 토큰에 대한 noExistUser 예외는 WebConfig 단에서 처리하는게 맞아보인다.
    // todo: delete, post, put의 엣지케이스를 잘 테스트해야할듯
    @Override
    public ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Integer boardId, String userId) {
        try {

            Optional<BoardEntity> boardEntityOptional = boardRepository.findById(boardId);
            if (boardEntityOptional.isEmpty()) {
                return DeleteBoardResponseDto.noExistBoard();
            }

            BoardEntity boardEntity = boardEntityOptional.get();

            Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
            if (userEntityOptional.isEmpty()) {
                return DeleteBoardResponseDto.noExistUser();
            }

            UserEntity userEntity = userEntityOptional.get();

            if (!boardEntity.getWriterId().equals(userEntity.getId())) {
                return DeleteBoardResponseDto.noPermission();
            }

            commentRepository.deleteByBoardId(boardId);
            joiningUserRepository.deleteByBoardId(boardId);
            boardRepository.delete(boardEntity);
            userEntity.setPenaltyUntil(LocalDateTime.now().plusMinutes(penaltyInMin));
            userRepository.save(userEntity);

            // 여기서 패널티 로직 들어가줘야 한다.


            return DeleteBoardResponseDto.success();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super GetBoardListResponseDto> getBoardList() {
        try {
            List<BoardListViewEntity> boardList = boardListViewRepository.findByOrderByWriteDatetimeDesc();
            return GetBoardListResponseDto.success(boardList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super GetBoardPreviewResponseDto> getBoardPreview(Integer boardId) {
        try {
            Optional<BoardListViewEntity> byId = boardListViewRepository.findById(boardId);
            if (byId.isEmpty()) return GetBoardPreviewResponseDto.noExistBoard();
            return GetBoardPreviewResponseDto.success(byId.get());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
    }


    @Override
    public ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardId) {
        try {
            Optional<BoardViewEntity> boardViewEntityOptional = boardViewRepository.findById(boardId);
            if (boardViewEntityOptional.isEmpty()) return GetBoardResponseDto.noExistBoard();

            return GetBoardResponseDto.success(boardViewEntityOptional.get());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardId, String userId) {
        try {

            if (!boardRepository.existsById(boardId)) {
                return GetCommentListResponseDto.noExistBoard();
            }

            if (!userRepository.existsById(userId)) {
                return GetCommentListResponseDto.noExistUser();
            }

            Optional<JoiningUserEntity> joiningUserEntityOptional = joiningUserRepository.findByUserId(userId);

            if (joiningUserEntityOptional.isEmpty()) {
                return GetCommentListResponseDto.noPermission();
            }

            JoiningUserEntity joiningUserEntity = joiningUserEntityOptional.get();

            if (!boardId.equals(joiningUserEntity.getBoardId())) {
                return GetCommentListResponseDto.noPermission();
            }

            List<CommentViewEntity> commentViewEntities = commentViewRepository.findByOrderByWriteDatetimeDesc();

            return GetCommentListResponseDto.success(commentViewEntities);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super GetJoinListResponseDto> getJoinList(Integer boardId) {
        try {
            if (!boardRepository.existsById(boardId)) GetJoinListResponseDto.noExistBoard();

            List<JoiningUserEntity> joiningUserEntities = joiningUserRepository.findByBoardId(boardId);

            List<UserEntity> userEntities = new ArrayList<>();

            for (JoiningUserEntity joiningUserEntity : joiningUserEntities) {
                userEntities.add(userRepository.findById(joiningUserEntity.getUserId()).orElseThrow());
            }

            return GetJoinListResponseDto.success(userEntities);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String userId) {
        try {
            // todo: penalty, joining 여부 체크하는 로직, post 후에 joining update하는 로직 필요함


            Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
            if (userEntityOptional.isEmpty()) {
                return PostBoardResponseDto.noExistUser();
            }

            UserEntity userEntity = userEntityOptional.get();
            // todo: penalty time, joining 여부 체크

            if (userEntity.getPenaltyUntil() != null) {
                if (userEntity.getPenaltyUntil().isAfter(LocalDateTime.now())) {
                    return PostBoardResponseDto.forbiddenToJoin();
                } else {
                    userEntity.setPenaltyUntil(null);
                    userEntity = userRepository.save(userEntity);
                }
            }

            if (joiningUserRepository.existsByUserId(userId)) {
                return PostBoardResponseDto.forbiddenToJoin();
            }


            BoardEntity boardEntity = boardRepository.save(new BoardEntity(dto, userId));
            Integer boardId = boardEntity.getId();


            JoiningUserEntity joiningUserEntity = new JoiningUserEntity(userId, boardId);
            joiningUserRepository.save(joiningUserEntity);
            // todo: joiningRepo 업데이트

            return PostBoardResponseDto.success(boardId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super PostCommentResponseDto> postComment(PostCommentRequestDto dto, Integer boardId, String userId) {
        try {
            if (!boardRepository.existsById(boardId)) {
                return GetCommentListResponseDto.noExistBoard();
            }

            if (!userRepository.existsById(userId)) {
                return GetCommentListResponseDto.noExistUser();
            }

            Optional<JoiningUserEntity> joiningUserEntityOptional = joiningUserRepository.findByUserId(userId);

            if (joiningUserEntityOptional.isEmpty()) {
                return GetCommentListResponseDto.noPermission();
            }

            JoiningUserEntity joiningUserEntity = joiningUserEntityOptional.get();

            if (!boardId.equals(joiningUserEntity.getBoardId())) {
                return GetCommentListResponseDto.noPermission();
            }

            CommentEntity commentEntity = new CommentEntity(dto, boardId, userId);
            commentRepository.save(commentEntity);

            return PostCommentResponseDto.success();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super PutJoinResponseDto> putJoin(Integer boardId, String userId) {

        // todo: 조인/취소 케이스 분류, boardEntity 수정 및 joiningUserRepo 수정
        // todo: 최대 4명까지만 가능한 것도 엣지케이스로 존재

        try {

            Optional<BoardEntity> boardEntityOptional = boardRepository.findById(boardId);
            if (boardEntityOptional.isEmpty()) {
                return PutJoinResponseDto.noExistBoard();
            }
            BoardEntity boardEntity = boardEntityOptional.get();

            Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
            if (userEntityOptional.isEmpty()) {
                return PutJoinResponseDto.noExistUser();
            }
            UserEntity userEntity = userEntityOptional.get();

            Optional<JoiningUserEntity> joiningUserEntityOptional = joiningUserRepository.findByUserId(userId);

            if (joiningUserEntityOptional.isEmpty()) {
                // 조인: board의 참여자 수 체크, 패널티 여부 체크
                if (boardEntity.getJoiningUserCnt() == 4) return PutJoinResponseDto.alreadyFullBoard();

                if (userEntity.getPenaltyUntil() != null) {
                    if (userEntity.getPenaltyUntil().isAfter(LocalDateTime.now())) {

                        return PutJoinResponseDto.forbiddenToJoin();
                    } else {
                        userEntity.setPenaltyUntil(null);
                        userEntity = userRepository.save(userEntity);
                    }
                }

                boardEntity.increaseJoiningUserCnt();
                if (boardEntity.getJoiningUserCnt() == 4) boardEntity.setClosed(true);
                boardRepository.save(boardEntity);

                JoiningUserEntity joiningUserEntity = new JoiningUserEntity(userId, boardId);
                joiningUserRepository.save(joiningUserEntity);
                return PutJoinResponseDto.success();
            } else {
                // 만약 joiningEntity의 boardId가 일치하지 않으면 이는 forbidden case임.
                // 취소: 글쓴 본인은 취소할 수 없다.
                //
                JoiningUserEntity joiningUserEntity = joiningUserEntityOptional.get();
                if (joiningUserEntity.getBoardId() == boardId) {
                    if (joiningUserEntity.getUserId().equals(boardEntity.getWriterId())) {
                        return PutJoinResponseDto.noPermission();
                    }

                    boardEntity.decreaseJoiningUserCnt();
                    boardEntity.setClosed(false);
                    boardRepository.save(boardEntity);

                    userEntity.setPenaltyUntil(LocalDateTime.now().plusMinutes(penaltyInMin));
                    userRepository.save(userEntity);

                    joiningUserRepository.delete(joiningUserEntity);
                    return PutJoinResponseDto.success();
                } else {
                    return PutJoinResponseDto.forbiddenToJoin();
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super PutLockResponseDto> putLock(Integer boardId, String userId) {
        try {
            Optional<BoardEntity> boardEntityOptional = boardRepository.findById(boardId);
            if (boardEntityOptional.isEmpty()) return PutLockResponseDto.noExistBoard();

            Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
            if (userEntityOptional.isEmpty()) return PutLockResponseDto.noExistUser();
            UserEntity userEntity = userEntityOptional.get();
            BoardEntity boardEntity = boardEntityOptional.get();
            if (!boardEntity.getWriterId().equals(userId)) return PutLockResponseDto.noPermission();

            boardEntity.setClosed(!boardEntity.isClosed());
            boardRepository.save(boardEntity);
            return PutLockResponseDto.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
    }
}
