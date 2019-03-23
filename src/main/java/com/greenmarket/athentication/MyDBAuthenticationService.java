package com.greenmarket.athentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyDBAuthenticationService {

	/*@Autowired
	UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user=userDao.getUserByName(userName);
		System.out.println("UserInfo= " + user);
		
		 if (user == null) {
	            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
	        }
		// [USER,ADMIN,..]
        String role= user.getRole().getRolename();
         
        List<GrantedAuthority> grantList= new ArrayList<GrantedAuthority>();
        if(role!=null)
        {
        	 GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + "USER");
        	 grantList.add(authority);
        	 if(!role.equals("USER"))
        	 {
        		 GrantedAuthority authority1 = new SimpleGrantedAuthority("ROLE_" +role);
        	 }
        }
        
        UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(user.getUsername(), //
                user.getPassword(),grantList);
 
        return userDetails;
	}*/
}
