package africa.semicolon.sendAm.services;

import africa.semicolon.sendAm.data.models.User;
import africa.semicolon.sendAm.data.repositories.UserRepository;
import africa.semicolon.sendAm.data.repositories.UserRepositoryImp;
import africa.semicolon.sendAm.dtos.requests.RegisterUserRequest;
import africa.semicolon.sendAm.dtos.responses.FindUserResponse;
import africa.semicolon.sendAm.dtos.responses.RegisterUserResponse;
import africa.semicolon.sendAm.exceptions.RegisterFailureException;
import africa.semicolon.sendAm.exceptions.UserNotFoundException;

public class UserServiceImpl implements  UserService{
    private UserRepository userRepository = new UserRepositoryImp();

    @Override
    public RegisterUserResponse register(RegisterUserRequest requestForm){
        requestForm.setEmailAddress(requestForm.getEmailAddress().toLowerCase());
        if (emailExists(requestForm.getEmailAddress())) throw new RegisterFailureException("Email is not valid");
        String email = requestForm.getEmailAddress();
        String fullName = requestForm.getFirstName() + " "+ requestForm.getLastName();
        String phone = requestForm.getPhoneNumber();
        String address = requestForm.getAddress();
        User user = new User(email, fullName, phone);
        user.setAddress(address);

        User savedUser = userRepository.save(user);

        RegisterUserResponse response = new RegisterUserResponse();
        response.setEmail(savedUser.getEmail());
        response.setFullName(savedUser.getFullName());

        return response;
    }

    private boolean emailExists(String emailAddress) {
        User user = userRepository.findByMail(emailAddress);
        if(user == null) return false;
        return true;
    }

    @Override
    public UserRepository getRepository(){
        return userRepository;

    }

    @Override
    public FindUserResponse findUserByEmail(String email){
        email = email.toLowerCase();
        User user = userRepository.findByMail(email);
//        create response
        if (user == null) throw new UserNotFoundException(email + "not found");
        FindUserResponse response = new FindUserResponse();
        response.setUserEmail(user.getEmail());
        response.setFullName(user.getFullName());

        return response;
    }

}
