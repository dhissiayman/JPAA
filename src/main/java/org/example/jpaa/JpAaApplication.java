package org.example.jpaa;

import org.example.jpaa.entities.Role;
import org.example.jpaa.entities.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.example.jpaa.service.UserService;

@SpringBootApplication
public class JpAaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpAaApplication.class, args);
    }

    @Bean
    CommandLineRunner start(UserService userService) {
        return args -> {
            // Create first user
            User u1 = new User();
            u1.setUsername("user1");
            u1.setPassword("123456");
            userService.addNewUser(u1);

            // Create second user
            User u2 = new User();
            u2.setUsername("admin1");
            u2.setPassword("123456");
            userService.addNewUser(u2);

            // Create a role
            Role role1 = new Role();
            role1.setRoleName("Etudiant");
            role1.setDesc("bal");
            userService.addNewRole(role1);

            // Assign roles to users
            userService.addRoleToUser("user1", "Etudiant");
            userService.addRoleToUser("admin1", "Etudiant");


            try{
                User user=userService.authenticate("user1", "123456");
              System.out.println(user.getUsername());
              System.out.println(user.getUserId());
              System.out.println("Roles=>");
              user.getRoles().forEach(role -> System.out.println(role));

            }

            catch(Exception e){
                e.printStackTrace();
            }

        };
    }
}
