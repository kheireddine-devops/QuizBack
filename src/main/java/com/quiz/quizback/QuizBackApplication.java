package com.quiz.quizback;

import com.quiz.quizback.config.properities.QuizConfig;
import com.quiz.quizback.domain.entities.Category;
import com.quiz.quizback.domain.entities.User;
import com.quiz.quizback.domain.enums.GenderEnum;
import com.quiz.quizback.domain.enums.RoleEnum;
import com.quiz.quizback.repositories.ICategoryRepository;
import com.quiz.quizback.repositories.IUserRepository;
import com.quiz.quizback.services.specs.ICategoryService;
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
    @Autowired
    private ICategoryRepository categoryRepository;

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


        categoryRepository.deleteAll();
        Category webCategory = new Category();
        webCategory.setName("Web");
        webCategory.setDescription("Testez vos connaissances en développement web avec notre quiz interactif !\nCe test couvre des sujets clés tels que HTML, CSS, JavaScript, et les frameworks populaires.\nQue vous soyez débutant ou expert, découvrez où vous en êtes et améliorez vos compétences en répondant à des questions variées et stimulantes");

        Category englishCategory = new Category();
        englishCategory.setName("English");
        englishCategory.setDescription("Évaluez vos compétences en anglais avec notre quiz interactif en ligne !\nCe test couvre la grammaire, le vocabulaire, la compréhension écrite, et plus encore.\nIdéal pour tous les niveaux, il vous aidera à identifier vos points forts et vos domaines à améliorer.\nPréparez-vous à un défi stimulant et améliorez votre maîtrise de l'anglais");

        Category javascriptCategory = new Category();
        javascriptCategory.setName("JavaScript");
        javascriptCategory.setDescription("Mettez à l'épreuve vos compétences en JavaScript et Angular avec notre quiz interactif !\nCe test couvre les concepts essentiels et avancés, des bases de JavaScript aux fonctionnalités spécifiques d'Angular.\nParfait pour les développeurs souhaitant évaluer et renforcer leurs connaissances, ce quiz vous offre un défi stimulant et éducatif");

        this.categoryRepository.save(webCategory);
        this.categoryRepository.save(englishCategory);

    }
}
