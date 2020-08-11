package com.sesoc.core.config;

public class SystemEnvironment {
	public final static String PROFILE_LOCAL = "local";
	public final static String PROFILE_SERVER = "server";
	
	private static String activeProfile;
	public final static String[] PROFILE_LIST = new String[] {
			PROFILE_LOCAL, PROFILE_SERVER
	};
	public static String getActiveProfile() {
		return activeProfile;
	}
	public static String setActiveProfile(String profile) {
		return activeProfile = profile;
	}
}
