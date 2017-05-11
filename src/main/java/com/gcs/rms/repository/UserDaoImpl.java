package com.gcs.rms.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	 public boolean changePassword(String username, String oldPass, String newPass)
	{
			String hql = "SELECT [user].[password] " + "FROM [user] "
							+ "WHERE [user].[user_name] = :username";		
		
	        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        String sql = "Select [password] from [user] Where [user_name] = :username";
	        String dataOldPass = sessionFactory
					        		.getCurrentSession()
					        		.createSQLQuery(sql)
					        		.setParameter("username", username)
					        		.list().get(0).toString();
					        		     		
	        if(encoder.matches(oldPass, dataOldPass))
	        {
	            newPass = encoder.encode(newPass);
	            sql = "UPDATE [user] set [password] = :password Where [user_name] = :username";
	            return (0 < sessionFactory
	            			.getCurrentSession()
	            			.createSQLQuery(sql)
	            			.setParameter("username", username)
	            			.setParameter("password", newPass)
	            			.executeUpdate());         			
	        }
	        return false;
	    }
}
