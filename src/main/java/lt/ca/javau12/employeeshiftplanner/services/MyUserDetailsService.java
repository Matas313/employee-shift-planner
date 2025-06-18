package lt.ca.javau12.employeeshiftplanner.services;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lt.ca.javau12.employeeshiftplanner.entities.UserBase;
import lt.ca.javau12.employeeshiftplanner.repositories.UserBaseRepository;


@Service
public class MyUserDetailsService implements UserDetailsService {

	private UserBaseRepository userBaseRepository;
	
	public MyUserDetailsService(UserBaseRepository userBaseRepository) {
		this.userBaseRepository = userBaseRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	    UserBase user = userBaseRepository.findByEmail(email)
	        .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

	    List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole().name()));

	    return new org.springframework.security.core.userdetails.User(
	        user.getEmail(),
	        user.getPassword(),
	        authorities
	    );
	}
}