package com.hnt.bookService.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hnt.bookService.entity.ERole;
import com.hnt.bookService.entity.Role;
import com.hnt.bookService.entity.User;
import com.hnt.bookService.repository.RoleRepository;
import com.hnt.bookService.repository.UserRepository;
import com.hnt.bookService.request.LogInRequest;
import com.hnt.bookService.request.SignUpRequest;
import com.hnt.bookService.response.JwtResponse;
import com.hnt.bookService.response.MessageResponse;
import com.hnt.bookService.security.jwt.JwtUtils;
import com.hnt.bookService.security.services.UserDetailsImpl;

/**
 * @author cogjava3185_MaheshMane
 * Here is Authentication done
 * SignUp and SignIn request Handled
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/userauth")
public class AuthorController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	JwtUtils jwtUtils;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LogInRequest logInRequest){
		Authentication authentication=authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(logInRequest.getUserName(), logInRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt=jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetailsImpl=(UserDetailsImpl) authentication.getPrincipal();
		List<String> roles=userDetailsImpl.getAuthorities().stream().map(e->e.getAuthority()).collect(Collectors.toList());
		
		return ResponseEntity.ok(new JwtResponse(jwt, 
				userDetailsImpl.getId(),
				userDetailsImpl.getName(),
				userDetailsImpl.getUsername(),
				userDetailsImpl.getEmailId(),
				roles));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> signupUser(@Valid @RequestBody SignUpRequest signUpRequest){
		if(Boolean.TRUE.equals(userRepository.existsByUserName(signUpRequest.getUserName()))) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Alert: UserName Already Exist..! Please try another with Username"));
		}
		
		if(Boolean.TRUE.equals(userRepository.existsByEmailId(signUpRequest.getEmailId()))) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Alert: Email Id Already Exist..! Please enter another EmailId"));
		}
		
		User user=new User(signUpRequest.getName(), signUpRequest.getEmailId(), signUpRequest.getUserName(), 
				passwordEncoder.encode(signUpRequest.getPassword()));
		
		Set<String> strRoles=signUpRequest.getRole();
		Set<Role> roles=new HashSet<>();
		
		if(strRoles==null) {
			Role userRole=roleRepository.findByName(ERole.ROLE_READER)
					.orElseThrow(()->new RuntimeException("Alert: UserRole is not Found"));
			roles.add(userRole);
		}else {
			strRoles.forEach(role->{
				if(role.equalsIgnoreCase("author")) {
					Role authorRole=roleRepository.findByName(ERole.ROLE_AUTHOR)
							.orElseThrow(()-> new RuntimeException("Alert: UserRole is not Found"));
					roles.add(authorRole);
				}else {
					Role readerRole=roleRepository.findByName(ERole.ROLE_READER)
							.orElseThrow(()-> new RuntimeException("Alert: UserRole is not Found"));
					roles.add(readerRole);
				}
			});
		}
		
		user.setRoles(roles);
		userRepository.save(user);
		
		return ResponseEntity.ok(new MessageResponse("User Registered Successfully..!"));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
