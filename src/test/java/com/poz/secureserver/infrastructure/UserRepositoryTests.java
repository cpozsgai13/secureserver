package com.poz.secureserver.infrastructure;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import com.poz.secureserver.infrastructure.model.Users;
import com.poz.secureserver.infrastructure.repository.UserRepository;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:postgresql://localhost:5432/staged",
        "spring.jpa.hibernate.ddl-auto=validate"
})
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@ActiveProfiles("test")
public class UserRepositoryTests {

    private Users testUser;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    UserRepository userRepo;

    @BeforeEach
    public void testCreateUser() {
        testUser = new Users.Builder()
                .name("test")
                .first("test")
                .last("test")
                .email("test@test.com")
                .deleted(false)
                .use_2fa(false)
                .active(false)
                .build();
        Users saved_user = userRepo.save(testUser);
        String out = String.format("Saved user {}, id {}", saved_user.getName(), saved_user.getId());
        System.out.println(out);
    }

    @Test
    void checkTestUser() {
        Optional<Users> check_user = userRepo.findById(testUser.getId());
        if (check_user.isPresent()) {
            System.out
                    .println(String.format("User {}, {} found.", check_user.get().getName(), check_user.get().getId()));
        }
    }

    @AfterEach
    public void tearDown() {
        userRepo.delete(testUser);
    }

}
