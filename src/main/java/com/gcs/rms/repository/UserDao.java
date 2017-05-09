package com.gcs.rms.repository;
import com.gcs.rms.model.Users;
public interface UserDao {
	Users findByUserName(String username);
}
