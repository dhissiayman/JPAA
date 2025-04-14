package org.example.jpaa.service;

import org.example.jpaa.entities.Role;
import org.example.jpaa.entities.User;




public interface UserService {
    User addNewUser(User user);
    Role addNewRole(Role role);
    User findUserByUsername(String username);
    Role findRoleByRolename(String rolename);
    void addRoleToUser(String username, String roleName  );
    User authenticate(String username, String password);

}
