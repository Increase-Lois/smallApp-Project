package africa.semicolon.sendAm.services;

import africa.semicolon.sendAm.dtos.requests.RegisterUserRequest;
import africa.semicolon.sendAm.dtos.responses.FindUserResponse;
import africa.semicolon.sendAm.dtos.responses.RegisterUserResponse;
import africa.semicolon.sendAm.exceptions.RegisterFailureException;
import africa.semicolon.sendAm.exceptions.SendAmAppException;
import africa.semicolon.sendAm.exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    private UserService userService;

    @BeforeEach
    public void testSetup(){
        userService = new UserServiceImpl();
    }

    private RegisterUserRequest createRegisterForm() {
        RegisterUserRequest registerForm = new RegisterUserRequest();
        registerForm.setFirstName("Joseph");
        registerForm.setLastName("Lois");
        registerForm.setEmailAddress("joe-lois@gmail.com");
        registerForm.setAddress("Juno building");
        registerForm.setPhoneNumber("18 billion dollars");
        return registerForm;
    }

    @Test
    void afterRegister_repositoryContainsOneElement(){
        //given
        RegisterUserRequest registerForm = createRegisterForm();
        
        //when
        userService.register(registerForm);

       //assert
        assertEquals(1,userService.getRepository().count());
    }



    @Test
    public void duplicateEmail_throwsExceptionTest(){
        RegisterUserRequest loisForm = createRegisterForm();
       //when
        userService.register(loisForm);
       // assert
        assertThrows(SendAmAppException.class, ()-> userService.register(loisForm));
        assertThrows(RegisterFailureException.class, ()-> userService.register(loisForm));
    }

    @Test
    public void duplicateEmailWithDifferentCase_throwsExceptionTest(){
        RegisterUserRequest loisForm = createRegisterForm();
        //when
        userService.register(loisForm);
        userService.getRepository().findByMail("jOE-lois@gmail.com");
        loisForm.setEmailAddress("jOE-lois@gmail.com");
        // assert
        assertThrows(SendAmAppException.class, ()-> userService.register(loisForm));
        assertThrows(RegisterFailureException.class, ()-> userService.register(loisForm));
    }

    @Test
    public void registrationReturnsCorrectResponseTest(){
        RegisterUserRequest loisForm = createRegisterForm();
        RegisterUserResponse response = userService.register(loisForm);
        assertEquals("Joseph Lois", response.getFullName());
        assertEquals("joe-lois@gmail.com",response.getEmail());
    }

    @Test
    public void findRegisteredUserByEmailTest(){
        RegisterUserRequest loisForm = createRegisterForm();
        userService.register(loisForm);

        FindUserResponse response = userService.findUserByEmail(loisForm.getEmailAddress().toLowerCase());

        assertEquals("Joseph Lois", response.getFullName());
        assertEquals("joe-lois@gmail.com", response.getUserEmail());
    }

    @Test
    public void findingUnregisteredEmail_throwsExceptionTest(){
        RegisterUserRequest loisForm = createRegisterForm();
        userService.register(loisForm);

        FindUserResponse result = userService.findUserByEmail(loisForm.getEmailAddress());
        assertThrows(UserNotFoundException.class,()->userService.findUserByEmail("ademiju@gmail.com"));
    }

    @Test
    public void findUserEmailIsNotCaseSensitiveTest(){
        RegisterUserRequest loisForm = createRegisterForm();
        userService.register(loisForm);

        FindUserResponse response = userService.findUserByEmail("joe-lois@gmail.com");

        assertEquals("Joseph Lois", response.getFullName());
        assertEquals("joe-lois@gmail.com", response.getUserEmail());
    }


}