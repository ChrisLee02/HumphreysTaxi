package com.example.demo.dto.request.board.pending;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PatchBoardRequestDto {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private String startingPoint;
    @NotBlank
    private String destinationPoint;
    @NotBlank
    private LocalDateTime departTime;
}
