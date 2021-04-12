package com.mldong.common.upload;

import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Configuration
@EnableConfigurationProperties(MinioProperties.class)
public class MinioUpload {
    @Autowired
    private MinioProperties minioProperties;
    private MinioClient minioClient = null;
    public MinioClient getMinioClient() {
        try {
            if(minioClient == null) {
                minioClient =
                        MinioClient.builder()
                                .endpoint(minioProperties.getUrl())
                                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                                .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return minioClient;
    }
    public boolean uploadObject(String bucket,String fileName,InputStream inputStream) {

        MinioClient minioClient = getMinioClient();
        PutObjectArgs putObjectArgs = PutObjectArgs.builder().bucket(bucket).object(fileName).stream(inputStream,0,-1).build();
        try {
            ObjectWriteResponse objectWriteResponse = minioClient.putObject(putObjectArgs);
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        } catch (InsufficientDataException e) {
            e.printStackTrace();
        } catch (InternalException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidResponseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (XmlParserException e) {
            e.printStackTrace();
        }
        return true;
    }
}
