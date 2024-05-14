package com.example.demo.common;

public interface ResponseCode {
    //200
    String SUCCESS = "SU";

    //400
    String VALIDATION_FAILED = "VF";
    String DUPLICATE_ID = "DI";
    String DUPLICATE_NICKNAME = "DN";
    String NOT_EXISTED_USER = "NU";
    String NOT_EXISTED_BOARD = "NB";

    //401
    String SIGN_IN_FAIL = "SF";
    String AUTHORIZATION_FAIL = "AF";

    //403
    String NO_PERMISSION = "NP";
    String FORBIDDEN_TO_JOIN = "FJ";
    String ALREADY_FUlL_BOARD = "AFB";
    //500
    String DATABASE_ERROR = "DBE";


}