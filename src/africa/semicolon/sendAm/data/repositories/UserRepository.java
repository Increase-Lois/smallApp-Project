package africa.semicolon.sendAm.data.repositories;

import africa.semicolon.sendAm.data.models.User;

import java.util.List;

public interface UserRepository {
    User save(User aUser);
    User findByMail(String mail);
    void delete(User aUser);
    void delete(String mail);
    List <User> findAll();

    int count();
}
