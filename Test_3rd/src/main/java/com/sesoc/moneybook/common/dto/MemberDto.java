package com.sesoc.moneybook.common.dto;

public class MemberDto {
	
	/** ȸ�� ���̵� */
	private String userId;
	/** ȸ�� �н����� */
	private String userPwd;
	
	
	/**
	 * ȸ�� ���̵�
	 * @return
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * ȸ�� ���̵�
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	/**
	 * ȸ�� �н�����
	 * @return
	 */
	public String getUserPwd() {
		return userPwd;
	}
	/**
	 * ȸ�� �н�����
	 * @param userPwd
	 */
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	
	
}
