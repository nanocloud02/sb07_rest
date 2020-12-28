package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.entities.Profile;
import com.example.entities.User;
import com.example.repositories.ProfileRepository;
import com.example.repositories.UserRepository;
import com.github.javafaker.Faker;

@SpringBootApplication
public class Sb07RestApplication implements ApplicationRunner {

	@Autowired
	private Faker faker;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProfileRepository profilRepository;

	public static void main(String[] args) {
		SpringApplication.run(Sb07RestApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Profile p = new Profile();
		p.setFirstName("Nano");
		p.setLastName("Cloud");
		Profile pr = profilRepository.save(p);
		for (int i = 0; i < 100; i++) {
			User user = new User();
			user.setUsername(faker.name().username());
			user.setPassword(faker.dragonBall().character());
//			user.setProfile(null);
//			if (i == 1) {
//				user.setProfile(pr);
//			}
			userRepository.save(user);
		}

		for (int i = 0; i < 6; i++) {
			System.out.println(6 - i - 1 + " ...");
		}
	}

}
