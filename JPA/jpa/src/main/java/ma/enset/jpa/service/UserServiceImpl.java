package ma.enset.jpa.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.enset.jpa.entities.Role;
import ma.enset.jpa.entities.User;
import ma.enset.jpa.repositories.RoleRepository;
import ma.enset.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional @AllArgsConstructor
public class UserServiceImpl implements UserService {
    RoleRepository roleRepository;
    UserRepository userRepository;

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
    public User findUserByUserName(String userName) {

        return userRepository.findByUserName(userName);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {

        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        User user=findUserByUserName(username);
        Role role=findRoleByRoleName(rolename);
        if(user.getRoles()!=null){
            user.getRoles().add(role);
            role.getUsers().add(user);
        }

    }

    @Override
    public User authenticate(String userName, String password) {
        User user=findUserByUserName(userName);
        if(user==null)throw new RuntimeException("bad credentials");
        if(user.getPassword().equals(password)){
            return user ;
        }
        throw new RuntimeException("bad credentials");
    }
}
