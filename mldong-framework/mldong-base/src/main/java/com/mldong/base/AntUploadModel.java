package com.mldong.base;

import lombok.Data;

import java.io.Serializable;

@Data
public class AntUploadModel implements Serializable {
    private String id;
    private String uid;
    private String name;
    private String url;
    private String status;
    private String bizType;
}
