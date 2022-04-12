package africa.semicolon.sendAm;

import africa.semicolon.sendAm.controllers.UserController;
import africa.semicolon.sendAm.dtos.requests.AddPackageRequest;
import africa.semicolon.sendAm.dtos.requests.RegisterUserRequest;
import africa.semicolon.sendAm.dtos.responses.FindUserResponse;
import africa.semicolon.sendAm.dtos.responses.RegisterUserResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Main {
    public static UserController userController = new UserController();
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
//        load options
//        loadOptions();
//        if option is register
//        load form
//        print output
//        load options
//        if options is search by email
//        ask for email
//        show
//        load options

    }
    private static void loadOptions(){
        String options= """
                A for Register
                B for find email
                """;
        String input = collectStringInput(options);
        switch (input.toLowerCase()){
            case "a"-> loadRegisterForm();
            case "b"-> askUserForEmail();
            default -> {
                display("get sense");
                loadOptions();
            }
        }
    }

    private static void addPackage(){
        AddPackageRequest packageToAdd = new AddPackageRequest();
    }

    private static void askUserForEmail() {
        String userEmail = collectStringInput("Enter the email you want to search for: ");
        FindUserResponse response = userController.getUserByEmail(userEmail);
        display(response.toString());
        loadOptions();
    }

    private static void loadRegisterForm(){
        RegisterUserRequest form = new RegisterUserRequest();
        form.setFirstName(collectStringInput("Enter your first name: "));
        form.setLastName(collectStringInput("Enter your last name: "));
        form.setEmailAddress(collectStringInput("Enter your email address: "));
        form.setPhoneNumber(collectStringInput("Enter your phone number : "));
        form.setAddress(collectStringInput("Enter your home address: "));
        RegisterUserResponse response = userController.registerNewUser(form);

    }
    private static String collectStringInput(String message){
        Scanner scanner = new Scanner(System.in);
        display(message);
        return scanner.nextLine();
    }
    private static void display(String message){
        System.out.println(message);
    }
}
