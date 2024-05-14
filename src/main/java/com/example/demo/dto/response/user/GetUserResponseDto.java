package com.example.demo.dto.response.user;

import com.example.demo.common.ResponseCode;
import com.example.demo.common.ResponseMessage;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.entity.UserEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
public class GetUserResponseDto extends ResponseDto {

    private String id;
    private String unit;
    private String username;
    private String profileImage;
    private String address;
    private String defaultDepartingPoint;
    private Integer joiningBoardId;
    private LocalDateTime penaltyUntil;


    public GetUserResponseDto(UserEntity userEntity, Integer joiningBoardId) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        this.id = userEntity.getId();
        this.unit = userEntity.getUnit();
        this.username = userEntity.getUsername();
        this.profileImage = userEntity.getProfileImage();
        this.address = userEntity.getAddress();
        this.defaultDepartingPoint = userEntity.getDefaultDepartingPoint();
        this.penaltyUntil = userEntity.getPenaltyUntil();
        this.joiningBoardId = joiningBoardId;
    }

    public static ResponseEntity<GetUserResponseDto> success(UserEntity userEntity, Integer joiningBoardId) {
        GetUserResponseDto result = new GetUserResponseDto(userEntity, joiningBoardId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistUser() {
        return ResponseDto.noExistUser();
    }


}
