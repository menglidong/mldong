package com.mldong.log;

import lombok.Data;

import java.util.Date;

@Data
public class LogParam {
    private Long userId;//	当前用户ID
    private String userName	;//	当前用户名
    private String requestNo	;//	当前请求编号
    private String ip	;//客户端ip
    private String brower	;//	客户端浏览器信息
    private String url ;//	请求地址;//
    private Date startTime	;//	请求开始时间
    private String body	;//	请求正文参数
    private String params	;//	请求参数
    private String resData	;//	响应数据
    private Date endTime	;//	结束时间
    private Long time	;//	请求响应时间
}