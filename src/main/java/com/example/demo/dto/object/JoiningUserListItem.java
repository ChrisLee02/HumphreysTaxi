package com.example.demo.dto.object;

import com.example.demo.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JoiningUserListItem {
    private String userId;
    private String unit;
    private String username;
    private String profileImage;


    public JoiningUserListItem(UserEntity userEntity) {
        this.userId = userEntity.getId();
        this.unit = userEntity.getUnit();
        this.username = userEntity.getUsername();
        this.profileImage = userEntity.getProfileImage();
    }

    public static List<JoiningUserListItem> getList(List<UserEntity> userEntities) {
        List<JoiningUserListItem> list = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            list.add(new JoiningUserListItem(userEntity));
        }
        return list;
    }

}
