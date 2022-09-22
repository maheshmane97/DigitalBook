package com.hnt.bookService.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hnt.bookService.entity.User;
import com.hnt.bookService.repository.UserRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user=userRepository.findByUserName(userName).orElseThrow(()->
		new UsernameNotFoundException("User not found with UserName"+userName));
		return UserDetailsImpl.build(user);
	}
	
	

}
