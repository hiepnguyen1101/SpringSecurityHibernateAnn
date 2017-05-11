package com.gcs.rms.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gcs.rms.model.UserRole;
import com.gcs.rms.repository.UserDao;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserDao userDao;
	
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(final String userName) 
			throws UsernameNotFoundException{
		//user hold data get from database
		com.gcs.rms.model.User user = (com.gcs.rms.model.User) userDao.findByUserName(userName);
		//get list authority
		List<GrantedAuthority> authorities =
                buildUserAuthoritory(user.getUserRoles());
		return buildUserForAuthentication(user, authorities);
	}
	
	/**
	 * Convert user model to user of spring security
	 * @param user model object
	 * @param authorities list granted authorities
	 * @return user object of spring security
	 */
	private User buildUserForAuthentication(com.gcs.rms.model.User user,
			List<GrantedAuthority> authorities){		
		return new User(user.getUserName(),
				user.getPassword().toString(),
				user.getState(),
				true, 
				true,
				true, 
				authorities);		
	}
	
	/**
	 * 
	 * @param userRoles
	 * @return
	 */
	
	private List<GrantedAuthority> buildUserAuthoritory(Set<UserRole> userRoles){
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		//build user's authorities
		for(UserRole userRole : userRoles){
			setAuths.add(new SimpleGrantedAuthority("ROLE_"+userRole.getRole().getName()));
		}
		List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(setAuths);
		return result;
	}	

}
