package com.example.csv.utils;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ResultList<T> implements Serializable {
    private String message ;

    private Integer code ;

    private Boolean success ;

    private List<T> data ;

    private Long timestamp ;

    private Integer errCode ;

    private String sessionId ;

    private String requestId ;

    private Integer currentPage ;

    private Integer pageSize ;

    private Long totalNum ;

    private Integer totalPages ;

    private String errMsg ;

    public ResultList() {
        timestamp = System.currentTimeMillis() ;
    }
}
