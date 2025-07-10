package com.poz.secureserver.infrastructure.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.poz.secureserver.infrastructure.model.Users;

@Service
@Configurable
public interface UserService {
    Users saveUser(Users user);
    Optional<Users> getUser(Long id);    
    List<Users> fetchUsers();

    void deleteUserById(Long userID);
    List<String> getUserList();
    public Boolean loginUser(String user, String password);
    public Boolean userExists(String userName);

    public Users registerUser(Users user, String clearPassword);
}
