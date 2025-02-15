package com.backend.pfe;

import com.backend.pfe.entites.Role;
import com.backend.pfe.entites.User;
import com.backend.pfe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class PfeApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(PfeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(userRepository.findByEmail("test $$$$"));
		User adminAccount = userRepository.findByRole(Role.ADMIN);
		System.out.println(adminAccount);
		if(null == adminAccount) {
			User user = new User();
			user.setFirstName("wassim");
			user.setLastName("rahali");
			user.setEmail("wassim.rahali@sofrecom.com");
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			user.setRole(Role.COLLABORATOR);
			userRepository.save(user);
		}
	}
}