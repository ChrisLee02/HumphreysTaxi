package com.example.demo.scheduler;

import com.example.demo.entity.BoardEntity;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.JoiningUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

// 하루에 한 번, 출발 날짜가 이미 지난 게시글은 청소한다.
@Component
@RequiredArgsConstructor
public class BoardScheduler {

    private final BoardRepository boardRepository;
    private final JoiningUserRepository joiningUserRepository;
    private final CommentRepository commentRepository;


    @Scheduled(cron = "0 0 0 * * *")
    public void cleanBoard() {
        List<BoardEntity> boardEntities = boardRepository.findAll();
        for (BoardEntity board : boardEntities) {
            if (board.getDepartTime().isBefore(LocalDateTime.now())) {
                deleteBoard(board);
            }
        }

    }

    private void deleteBoard(BoardEntity boardEntity) {
        commentRepository.deleteByBoardId(boardEntity.getId());
        joiningUserRepository.deleteByBoardId(boardEntity.getId());
        boardRepository.delete(boardEntity);
    }


}
