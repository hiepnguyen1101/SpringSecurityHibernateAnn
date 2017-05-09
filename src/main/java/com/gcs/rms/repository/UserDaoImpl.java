package com.gcs.rms.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gcs.rms.model.Users;

@Repository 
public class UserDaoImpl implements UserDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public Users findByUserName(String username) {
		List<Users> users = new ArrayList<Users>();
		users = sessionFactory.getCurrentSession()
				.createSQLQuery("select {users.*} from users {users} where username=?")
				.addEntity("users",Users.class)
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
