package com.mldong.common.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class JwtToken {
	@Autowired
	private JwtProperties properties;
	/**
	 * 创建token
	 * @param userId
	 * @param userName
	 * @return
	 */
	public String generateToken(Long userId,String userName) {
		 Date now = new Date();  
	        Algorithm algorithm = Algorithm.HMAC256(properties.getSecret()); //算法  
	        String token = JWT.create()  
	            .withIssuer(properties.getIssuer()) //签发人  
	            .withIssuedAt(now) //签发时间  
	            .withExpiresAt(new Date(now.getTime() + properties.getExpireTime())) //过期时间  
	            .withClaim("userId", userId)
	            .withClaim("userName", userName) //保存身份标识  
	            .sign(algorithm); 
	    return token;
	}
	/** 
     * 验证token 
     */  
    public boolean verify(String token){  
        try {  
            Algorithm algorithm = Algorithm.HMAC256(properties.getSecret()); //算法  
            JWTVerifier verifier = JWT.require(algorithm)  
                    .withIssuer(properties.getIssuer())  
                    .build();  
            verifier.verify(token);  
            return true;  
        } catch (Exception ex){  
            ex.printStackTrace(); 
        }  
        return false;  
    }  
    /** 
     * 从token获取username 
     */  
    public String getUserName(String token){  
        try{  
            return JWT.decode(token).getClaim("userName").asString();  
        }catch(Exception ex){  
            ex.printStackTrace();  
        }  
        return "";  
    }  
    /**
     * token 获取用户id
     * @param token
     * @return
     */
    public Long getUserId(String token){  
        try{  
            return JWT.decode(token).getClaim("userId").asLong();  
        }catch(Exception ex){  
            ex.printStackTrace();  
        }  
        return 0L;  
    }  
}