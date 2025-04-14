package org.example.jpaa.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.jpaa.entities.Role;
import org.example.jpaa.entities.User;
import org.example.jpaa.repositories.RoleRepository;
import org.example.jpaa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    // public UserServiceImpl(RoleRepository roleRepository,UserRepository userRepository) {
    //     this.roleRepository = roleRepository;
    //    this.userRepository = userRepository;
    //}*


    @Override
    public User addNewUser(User user) {
        user.setUserId(UUID.randomUUID().toString());


        return userRepository.save(user);
    }

    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User findUserByUsername(String username) {


        return userRepository.findByUsername(username);
    }

    @Override
    public Role findRoleByRolename(String rolename) {

        return roleRepository.findByRoleName(rolename);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = this.findUserByUsername(username);
        Role role = this.findRoleByRolename(roleName);
        if (user.getRoles() != null) {
            user.getRoles().add(role);
            role.getUsers().add(user);
        }
        //userRepository.save(user);
    }

    @Override
    public User authenticate(String username, String password) {
        User user=this.userRepository.findByUsername(username);
        if(user==null) {throw new RuntimeException("User not found");}
        if (user.getPassword().equals(password)) {
            return user;
        }
        throw new RuntimeException("Invalid username or password");
    }
}
