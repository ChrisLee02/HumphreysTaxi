package com.example.demo.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchUserRequestDto {
    @NotBlank
    private String unit;
    @NotBlank
    private String username;
    private String profile_image;
    private String address;
    private String defaultDepartingPoint;
}
