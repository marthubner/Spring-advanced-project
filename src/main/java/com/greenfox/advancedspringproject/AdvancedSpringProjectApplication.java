package com.greenfox.advancedspringproject;

import com.greenfox.advancedspringproject.model.Role;
import com.greenfox.advancedspringproject.model.User;
import com.greenfox.advancedspringproject.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class AdvancedSpringProjectApplication implements CommandLineRunner {

    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(AdvancedSpringProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user = User.builder()
                .firstname("Martin")
                .lastname("Hübner")
                .role(Role.ADMIN)
                .email("marthubner@gmail.com")
                .password("$2a$10$9g1X9rp6meCML3g/h32MyeQ369SEh/hQpZb82eqjpvI71xCIdPAlG")
                .build();
        userRepository.save(user);
    }


}
