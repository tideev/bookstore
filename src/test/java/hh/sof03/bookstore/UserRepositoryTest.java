package hh.sof03.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.sof03.bookstore.domain.User;
import hh.sof03.bookstore.domain.UserRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    UserRepository uRepository;

    @Test //testataan UserRepon save()- ja findByUsername()-metodien toimivuutta
    public void testCreateAndSearchUser() {
        User user = new User("testuser", "passwordhash", "USER", "test@example.com");
        uRepository.save(user);
        assertThat(user.getId()).isNotNull();
    
        User foundUser = uRepository.findByUsername("testuser");
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getUsername()).isEqualTo("testuser");
    }
    

    @Test // testataan UserRepon delete()-metodin toimivuutta
    public void testDeleteUser() {
        User user = new User("testuser", "passwordhash", "USER", "test@example.com");
        uRepository.save(user);

        Long userId = user.getId();
        assertThat(uRepository.findById(userId)).isPresent();

        uRepository.deleteById(userId);
        assertThat(uRepository.findById(userId)).isEmpty();
    }
}
