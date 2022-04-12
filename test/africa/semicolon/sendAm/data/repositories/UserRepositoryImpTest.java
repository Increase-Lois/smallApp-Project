package africa.semicolon.sendAm.data.repositories;

import africa.semicolon.sendAm.data.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryImpTest {
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository = new UserRepositoryImp();
    }

    @Test
    void repositorySaveTest() {
        //given there is a user;
        User aUser = new User("increasebruce@gmail.com", "increase lois", "08187141669");
        //when i try to save in the user-repository
        User savedUser = userRepository.save(aUser);
        //assert that the returned  has an email;
        assertEquals("increasebruce@gmail.com", savedUser.getEmail());
        assertEquals(1, userRepository.count());
    }

    @Test
    void repositoryFindByEmailTest() {
        User firstUser = new User("hardeymiju@gmail.com", "Ademijuwonlo", "08165563818");
        User secondUser = new User("deedeji@yahoo.com", "Adeola", "07030908211");
        User thirdUser = new User("sannidami@hotmail.com", "Soj", "08080903111");

        userRepository.save(firstUser);
        userRepository.save(secondUser);
        userRepository.save(thirdUser);

        User foundUser = userRepository.findByMail("hardeymiju@gmail.com");

        assertEquals("hardeymiju@gmail.com", foundUser.getEmail());
    }

    @Test
    void deleteByEmailTest() {
        User firstUser = new User("hardeymiju@gmail.com", "Ademijuwonlo", "08165563818");
        User secondUser = new User("deedeji@yahoo.com", "Adeola", "07030908211");
        User thirdUser = new User("sannidami@hotmail.com", "Soj", "08080903111");
        userRepository.save(firstUser);
        userRepository.save(secondUser);
        userRepository.save(thirdUser);

        userRepository.delete("sannidami@hotmail.com");
        assertEquals(3, userRepository.count());
    }

    @Test
    void findByEmailWorks_AfterADeleteTest(){
        User firstUser = new User("hardeymiju@gmail.com", "Ademijuwonlo", "08165563818");
        User secondUser = new User("deedeji@yahoo.com", "Adeola", "07030908211");
        User thirdUser = new User("sannidami@hotmail.com", "Soj", "08080903111");
        userRepository.save(firstUser);
        userRepository.save(secondUser);
        userRepository.save(thirdUser);

        userRepository.delete("deedeji@yahoo.com");

        User foundUser = userRepository.findByMail("deedeji@yahoo.com");
        assertNull(foundUser);
    }

    @Test
    void saveAfterADelete_givesCorrectUserEmailTest(){
        User firstUser = new User("hardeymiju@gmail.com", "Ademijuwonlo", "08165563818");
        User secondUser = new User("deedeji@yahoo.com", "Adeola", "07030908211");
        User thirdUser = new User("sannidami@hotmail.com", "Soj", "08080903111");
        userRepository.save(firstUser);
        userRepository.save(secondUser);
        userRepository.save(thirdUser);

        userRepository.delete("deedeji@yahoo.com");
        User savedUser = userRepository.save(new User("tomison@gmail.com","tomi","09092127899"));
        assertEquals("tomison@gmail.com", savedUser.getEmail());
    }

    @Test
    void deleteByUserTest(){
        User firstUser = new User("hardeymiju@gmail.com", "Ademijuwonlo", "08165563818");
        User secondUser = new User("deedeji@yahoo.com", "Adeola", "07030908211");
        User thirdUser = new User("sannidami@hotmail.com", "Soj", "08080903111");
        userRepository.save(firstUser);
        userRepository.save(secondUser);
        userRepository.save(thirdUser);

        userRepository.delete(thirdUser);
        assertEquals(3,userRepository.count());
        assertNull(userRepository.findByMail("sannidami@hotmail.com"));
    }

    @Test
    void findAllTest(){
        User firstUser = new User("hardeymiju@gmail.com", "Ademijuwonlo", "08165563818");
        User secondUser = new User("deedeji@yahoo.com", "Adeola", "07030908211");
        User thirdUser = new User("sannidami@hotmail.com", "Soj", "08080903111");
        userRepository.save(firstUser);
        userRepository.save(secondUser);
        userRepository.save(thirdUser);

        List<User> all = userRepository.findAll();
        assertEquals(3,all.size());
    }


}


