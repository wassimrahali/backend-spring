    package com.backend.pfe.service.impl;

    import com.backend.pfe.repository.UserRepository;
    import com.backend.pfe.service.UserService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.core.userdetails.UsernameNotFoundException;
    import org.springframework.stereotype.Service;

    @Service
    @RequiredArgsConstructor
    public class UserServiceImpl implements UserService {
        private  UserRepository userRepository;

        public UserDetailsService userDetailsService() {
            return new UserDetailsService() {
                @Override
                public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                    return userRepository.findByEmail(username)
                            .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
                }
            };
        }


    }
