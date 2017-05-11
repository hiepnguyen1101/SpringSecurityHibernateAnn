package com.gcs.rms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gcs.rms.repository.UserDao;

@Repository
public class AccountServiceImpl implements IAcccountService {
	@Autowired
	UserDao userDao; 
	public boolean changePassword(String id, String oldPass, String newPass) {
		return userDao.changePassword(id, oldPass, newPass);
	}

}
