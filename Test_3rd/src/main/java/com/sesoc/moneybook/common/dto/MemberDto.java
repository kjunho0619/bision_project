package com.sesoc.moneybook.common.dto;

public class MemberDto {
	
	/** 회원 아이디 */
	private String userId;
	/** 회원 패스워드 */
	private String userPwd;
	
	
	/**
	 * 회원 아이디
	 * @return
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 회원 아이디
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	/**
	 * 회원 패스워드
	 * @return
	 */
	public String getUserPwd() {
		return userPwd;
	}
	/**
	 * 회원 패스워드
	 * @param userPwd
	 */
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	
	
}
