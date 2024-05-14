package com.example.demo.entity;


import com.example.demo.entity.primaryKey.JoiningUserPk;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "joining_user")
@Table(name = "joining_user")
@IdClass(JoiningUserPk.class)
public class JoiningUserEntity {
    @Id
    private String userId;
    @Id
    private int boardId;
}
