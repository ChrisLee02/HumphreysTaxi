package com.example.demo.service;

import com.example.demo.dto.request.board.PostBoardRequestDto;
import com.example.demo.dto.request.board.PostCommentRequestDto;
import com.example.demo.dto.response.board.*;
import org.springframework.http.ResponseEntity;

public interface BoardService {
    ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Integer boardId, String userId);

    ResponseEntity<? super GetBoardListResponseDto> getBoardList();

    ResponseEntity<? super GetBoardPreviewResponseDto> getBoardPreview(Integer boardId);

    ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardId);

    ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardId, String userId);

    ResponseEntity<? super GetJoinListResponseDto> getJoinList(Integer boardId);

    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String userId);

    ResponseEntity<? super PostCommentResponseDto> postComment(PostCommentRequestDto dto, Integer boardId, String userId);

    ResponseEntity<? super PutJoinResponseDto> putJoin(Integer boardId, String userId);
}
