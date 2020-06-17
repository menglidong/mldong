# 七牛上传

**接口地址**:`https://up-z2.qiniup.com`


**请求方式**:`POST`


**请求数据类型**:`multipart/form-data`

**响应数据类型**:`*/*`

**接口描述**:七牛上传


**关联接口**:
sys-上传记录管理->创建上传凭证
`/sys/uploadRecord/createUploadToken`

**请求参数**:


| 参数名称 | 参数说明 | in    | 是否必须 | 数据类型 |
| -------- | -------- | ----- | -------- | -------- |
|x:bizType|自定义参数，业务类型|formData|true|string|
|x:bizId|自定义参数，业务id，创建凭证时返回|formData|true|string|
|x:baseUrl|自定义参数，基础地址，创建凭证时返回|formData|false|string|
|token|上传凭证，创建凭证时返回|formData|true|string|
|file|要上传的文件|formData|true|file|

**响应示例**:

```javascript
{
    "code": 0,
    "data": {
        "baseUrl": "http://qiniu.mldong.com/",
        "bizId": "7f07a3cc-77ba-47b9-af74-88b2631f2bc4",
        "bizType": "avatar",
        "fileExt": ".png",
        "fileName": "qrcode.png",
        "fileSize": "497",
        "mimeType": "image/png",
        "url": "avatar/202006/50d0f9e5-fa60-4551-a0d9-714923575637.png"
    },
    "msg": "上传回调处理成功"
}
```

**响应错误示例一:**

当token过期时

``` json
{
    "error": "expired token"
}
```

**响应错误示例二:**

当七牛调自定义回调接口失败时

``` json
{
    "error": "{\"callback_url\":\"http://api.mldong.com/api/sys/uploadRecord/handleCallback\",\"callback_bodyType\":\"application/json\",\"callback_body\":\"{\\\"bizType\\\":\\\"avatar\\\",\\\"baseUrl\\\":\\\"http://qiniu.mldong.com/\\\",\\\"url\\\":\\\"avatar/202006/7e64db79-d1e3-4ed8-8901-f163b5c78cad.png\\\",\\\"fileSize\\\":\\\"497\\\",\\\"mimeType\\\":\\\"image/png\\\",\\\"fileName\\\":\\\"qrcode.png\\\",\\\"fileExt\\\":\\\".png\\\"}\",\"token\":\"\",\"err_code\":500,\"error\":\"Internal Server Error\",\"hash\":\"Fsuyl1Sy-4yMtTR4SkiILwAiFof2\",\"key\":\"avatar/202006/7e64db79-d1e3-4ed8-8901-f163b5c78cad.png\"}"
}
```

