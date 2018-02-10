package com.neeraj.rest.webservices.restwebservices.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

	private static List<User> list=new ArrayList<>();
	private static int count=3;
	static {
		list.add(new User(1,"Neeraj",new Date()));
		list.add(new User(2,"Abhiraj",new Date()));
		list.add(new User(3,"Naveen",new Date()));
		
	}
	
	public List<User> findAll(){
		return list;
	}
	public User findOne(int id) {
		for(User user:list) {
			if(user.getId()==id) {
				return user;
			}
			
		}
		return null;
	}
	
	public User addUser(User user) {
		if(user.getId()==null) {
			user.setId(++count);
			
			
		}
		list.add(user);
		return user;
	}
	public User deleteUserById(int id) {
		Iterator<User> iterator=list.iterator();
		while(iterator.hasNext()) {
			User user=(User) iterator.next();
			if(user.getId()==id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
}
