package com.example.csv.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
    private String message ;

    private Integer code ;

    private Boolean success ;

    private T data ;

    private Long timestamp ;

    private Integer errCode ;

    private String sessionId ;

    private String requestId ;

    private String errMsg ;

    public Result() {
        timestamp = System.currentTimeMillis() ;
    }
}
