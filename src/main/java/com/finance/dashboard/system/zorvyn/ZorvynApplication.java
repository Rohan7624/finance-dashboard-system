package com.finance.dashboard.system.zorvyn;

import com.finance.dashboard.system.zorvyn.entity.User;
import com.finance.dashboard.system.zorvyn.model.Role;
import com.finance.dashboard.system.zorvyn.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ZorvynApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZorvynApplication.class, args);
	}
	@Bean
	CommandLineRunner init(UserRepository repo, PasswordEncoder encoder) {
		return args -> {
			if (repo.findByEmail("admin@gmail.com").isEmpty()) {

				User user = new User();
				user.setName("Admin");
				user.setEmail("admin@gmail.com");
				user.setPassword(encoder.encode("admin123"));
				user.setRole(Role.ADMIN);
				user.setActive(true);

				repo.save(user);

				System.out.println("✅ Default Admin Created");
			}
		};
	}
}
