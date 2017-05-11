package com.gcs.rms.service;

public interface IAcccountService {
	boolean changePassword (String username, String oldPass, String newPass);
}
