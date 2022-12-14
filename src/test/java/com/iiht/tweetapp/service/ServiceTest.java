package com.iiht.tweetapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import com.iiht.tweetapp.exception.UnauthorizedException;
import com.iiht.tweetapp.model.UserData;
import com.iiht.tweetapp.repository.UserRepository;
import com.iiht.tweetapp.seviceimpl.CustomerDetailsService;

@ExtendWith(MockitoExtension.class)
class ServiceTest {

	UserDetails userdetails;
	
	@Mock
	UserRepository userservice;
	
	@InjectMocks
	CustomerDetailsService custdetailservice;

	
	

	@Test
	 void loadUserByUsernameTest() {
		
		UserData user1=new UserData("rituparna", "M", "rituparna", "rituparna", 0);
		Optional<UserData> data =Optional.of(user1) ;
		when(userservice.findById("rituparna")).thenReturn(data);
		UserDetails loadUserByUsername2 = custdetailservice.loadUserByUsername("rituparna");
		assertEquals(user1.getUserName(),loadUserByUsername2.getUsername());
	}
	@Test
	 void loadUserByUsernameTestFail() {
		
		Optional<UserData> data =Optional.ofNullable(null) ;
		when(userservice.findById("rituparna")).thenReturn(data);
		assertThrows( UnauthorizedException.class,()->custdetailservice.loadUserByUsername("rituparna"));
	}
	
	
	
	@Test
	void userNotFound() {
		
		when(userservice.findById("rituparna")).thenReturn(null);
		assertThrows( UnauthorizedException.class,()->custdetailservice.loadUserByUsername("rituparna"));
	}

	
}
