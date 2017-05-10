package com.gcs.rms.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gcs.rms.model.User;

@Repository 
public class UserDaoImpl implements UserDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public User findByUserName(String username) {
		List<User> users = new ArrayList<User>();
		users = sessionFactory.getCurrentSession()
				.createSQLQuery("select * from [user] where user_name=?")
				.addEntity("user",User.class)
				.setParameter(0, username)
				.list(); 

		if(users.size()>0){
			return users.get(0);
		}
		else {
			return null;
		}
	}
}
