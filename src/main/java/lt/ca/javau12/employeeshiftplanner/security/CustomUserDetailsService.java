package lt.ca.javau12.employeeshiftplanner.security;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import config.CustomUserDetails;
import lt.ca.javau12.employeeshiftplanner.repositories.UserBaseRepository;

@Service
@Primary
public class CustomUserDetailsService implements UserDetailsService {

    private final UserBaseRepository userRepository;

    public CustomUserDetailsService(UserBaseRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
