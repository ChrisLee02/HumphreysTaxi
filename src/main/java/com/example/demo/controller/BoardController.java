package com.example.demo.controller;

import com.example.demo.dto.request.board.PostBoardRequestDto;
import com.example.demo.dto.request.board.PostCommentRequestDto;
import com.example.demo.dto.response.board.*;
import com.example.demo.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/board")
public class BoardController {

    private final BoardService boardService;

    @DeleteMapping("/{boardId}")
    public ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(
            @PathVariable Integer boardId,
            @AuthenticationPrincipal String userId
    ) {
        return boardService.deleteBoard(boardId, userId);
    }

    @GetMapping("/boards")
    public ResponseEntity<? super GetBoardListResponseDto> getBoardList(
    ) {
        return boardService.getBoardList();
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<? super GetBoardResponseDto> getBoard(
            @PathVariable Integer boardId
    ) {
        return boardService.getBoard(boardId);
    }

    @GetMapping("/{boardId}/preview")
    public ResponseEntity<? super GetBoardPreviewResponseDto> getBoardPreview(
            @PathVariable Integer boardId
    ) {
        return boardService.getBoardPreview(boardId);
    }

    @GetMapping("/{boardId}/join")
    public ResponseEntity<? super GetJoinListResponseDto> getJoinList(
            @PathVariable Integer boardId
    ) {
        return boardService.getJoinList(boardId);
    }

    @GetMapping("/{boardId}/comments")
    public ResponseEntity<? super GetCommentListResponseDto> getCommentList(
            @PathVariable Integer boardId,
            @AuthenticationPrincipal String userId
    ) {
        return boardService.getCommentList(boardId, userId);
    }


    @PostMapping("")
    public ResponseEntity<? super PostBoardResponseDto> postBoard(
            @RequestBody @Valid PostBoardRequestDto dto,
            @AuthenticationPrincipal String userId
    ) {
        return boardService.postBoard(dto, userId);
    }

    @PostMapping("/{boardId}/comment")
    public ResponseEntity<? super PostCommentResponseDto> postBoard(
            @RequestBody @Valid PostCommentRequestDto dto,
            @PathVariable Integer boardId,
            @AuthenticationPrincipal String userId
    ) {
        return boardService.postComment(dto, boardId, userId);
    }


    @PutMapping("/{boardId}/join")
    public ResponseEntity<? super PutJoinResponseDto> putJoin(
            @PathVariable Integer boardId,
            @AuthenticationPrincipal String userId
    ) {
        return boardService.putJoin(boardId, userId);
    }

    @PutMapping("/{boardId}/lock")
    public ResponseEntity<? super PutLockResponseDto> putLock(
            @PathVariable Integer boardId,
            @AuthenticationPrincipal String userId
    ) {
        return boardService.putLock(boardId, userId);
    }


}
