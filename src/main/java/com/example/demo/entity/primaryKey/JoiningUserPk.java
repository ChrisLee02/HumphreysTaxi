package com.example.demo.entity.primaryKey;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JoiningUserPk implements Serializable {

    @Column(name = "user_id")
    private String userId;

    @Column(name = "board_id")
    private int boardId;


}
