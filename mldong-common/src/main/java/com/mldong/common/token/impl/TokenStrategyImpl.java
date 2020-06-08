package com.mldong.common.token.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mldong.common.jwt.JwtToken;
import com.mldong.common.token.TokenStrategy;

@Component
public class TokenStrategyImpl implements TokenStrategy{
	@Autowired
	private JwtToken jwtToken;

	@Override
	public String generateToken(Long userId, String userName) {
		return jwtToken.generateToken(userId, userName);
	}
	@Override
	public boolean verifyToken(String token) {
		return jwtToken.verify(token);
	}
	@Override
	public Long getUserId(String token) {
		return jwtToken.getUserId(token);
	}
	@Override
	public String getUserName(String token) {
		return jwtToken.getUserName(token);
	}
}
