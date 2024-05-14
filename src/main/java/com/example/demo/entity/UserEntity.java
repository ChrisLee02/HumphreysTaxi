package com.example.demo.entity;

import com.example.demo.dto.request.auth.SignUpRequestDto;
import com.example.demo.dto.request.user.PatchUserRequestDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "user")
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserEntity {
    @Id
    private String id;
    private String unit;
    private String username;
    private String password;
    private LocalDateTime penaltyUntil;
    private String profileImage;
    private String address;
    private String defaultDepartingPoint;
    private LocalDateTime createdAt;

    public UserEntity(SignUpRequestDto dto) {
        this.id = dto.getId();
        this.unit = dto.getUnit();
        this.username = dto.getUsername();
        this.password = dto.getPassword();
        this.penaltyUntil = null;
        this.profileImage = null;
        this.address = dto.getAddress();
        this.defaultDepartingPoint = dto.getDefaultDepartingPoint();
        this.createdAt = LocalDateTime.now();
    }

    //FOR TEST
    public UserEntity(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public void setPenaltyUntil(LocalDateTime penaltyUntil) {
        this.penaltyUntil = penaltyUntil;
    }

    public void patchUserEntity(PatchUserRequestDto dto) {
        this.unit = dto.getUnit();
        this.username = dto.getUsername();
        this.profileImage = dto.getProfile_image();
        this.address = dto.getAddress();
        this.defaultDepartingPoint = dto.getDefaultDepartingPoint();
    }


}
