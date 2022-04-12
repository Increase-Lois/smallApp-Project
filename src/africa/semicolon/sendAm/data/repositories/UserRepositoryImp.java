package africa.semicolon.sendAm.data.repositories;

import africa.semicolon.sendAm.data.models.Package;
import africa.semicolon.sendAm.data.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImp implements UserRepository{
    private List<User> database = new ArrayList<>();
    private int count;

    @Override
    public User save(User aUser) {
        database.add(aUser);
        count++;
        return aUser;
    }


    @Override
    public User findByMail(String mail) {
        for (User aUser: database){
            if (aUser.getEmail().equals(mail))
                return aUser;
        }
        return null;
    }


    @Override
    public void delete(User aUser) {
        database.remove(aUser);

    }

    @Override
    public void delete(String mail) {
        User foundUser = findByMail(mail);
        delete(foundUser);

    }

    @Override
    public List<User> findAll() {
        return database;
    }

    @Override
    public int count() {
        return count;
    }
}
