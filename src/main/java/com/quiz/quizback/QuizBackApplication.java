package com.quiz.quizback;

import com.quiz.quizback.config.properities.QuizConfig;
import com.quiz.quizback.domain.entities.User;
import com.quiz.quizback.domain.enums.GenderEnum;
import com.quiz.quizback.domain.enums.RoleEnum;
import com.quiz.quizback.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

@SpringBootApplication
@EnableConfigurationProperties({QuizConfig.class})
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
public class QuizBackApplication implements CommandLineRunner {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(QuizBackApplication.class, args);
    }

    @Override
    public void run(String... args) {
        User kheireddineAdmin = new User();
        kheireddineAdmin.setFirstname("Kheireddine");
        kheireddineAdmin.setLastname("Mechergui");
        kheireddineAdmin.setEmail("kheireddine.dev.ops@gmail.com");
        kheireddineAdmin.setGender(GenderEnum.MALE);
        kheireddineAdmin.setPassword(passwordEncoder.encode("admin"));
        kheireddineAdmin.setRole(RoleEnum.ROLE_ADMIN);

        User kheireddineUser = new User();
        kheireddineUser.setFirstname("Kheireddine");
        kheireddineUser.setLastname("Mechergui");
        kheireddineUser.setEmail("kheireddine.dev.ops@gmail.fr");
        kheireddineUser.setGender(GenderEnum.MALE);
        kheireddineUser.setPassword(passwordEncoder.encode("user"));
        kheireddineUser.setRole(RoleEnum.ROLE_USER);

        User sirineAdmin = new User();
        sirineAdmin.setFirstname("Sirine");
        sirineAdmin.setLastname("Srairi");
        sirineAdmin.setEmail("sirine.srairi10@gmail.com");
        sirineAdmin.setGender(GenderEnum.FEMALE);
        sirineAdmin.setPassword(passwordEncoder.encode("admin"));
        sirineAdmin.setRole(RoleEnum.ROLE_ADMIN);

        User sirineUser = new User();
        sirineUser.setFirstname("Sirine");
        sirineUser.setLastname("Srairi");
        sirineUser.setEmail("sirine.srairi10@gmail.fr");
        sirineUser.setGender(GenderEnum.FEMALE);
        sirineUser.setPassword(passwordEncoder.encode("user"));
        sirineUser.setRole(RoleEnum.ROLE_USER);

        userRepository.deleteAll();
        this.userRepository.save(kheireddineAdmin);
        this.userRepository.save(kheireddineUser);
        this.userRepository.save(sirineAdmin);
        this.userRepository.save(sirineUser);
    }
}
