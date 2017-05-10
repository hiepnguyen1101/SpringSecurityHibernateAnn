package com.gcs.rms.repository;

import com.gcs.rms.model.User;

public interface UserDao {
	User findByUserName(String username);
}
