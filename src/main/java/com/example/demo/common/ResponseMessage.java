package com.example.demo.common;

public interface ResponseMessage {
    //200
    String SUCCESS = "Success.";

    //400
    String VALIDATION_FAILED = "Validation failed.";
    String DUPLICATE_ID = "Duplicated ID.";
    String DUPLICATE_NICKNAME = "Duplicated nickname.";
    String NOT_EXISTED_USER = "This user does not exist.";
    String NOT_EXISTED_BOARD = "This board does not exist.";

    //401
    String SIGN_IN_FAIL = "Login information mismatch.";
    String AUTHORIZATION_FAIL = "Authorization Failed.";

    //403
    String NO_PERMISSION = "Do not have permission.";
    String FORBIDDEN_TO_JOIN = "You are on penalty or on the joining state of another board.";
    String ALREADY_FUlL_BOARD = "The board is already Full";
    //500
    String DATABASE_ERROR = "Database error.";
}