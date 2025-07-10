package com.poz.secureserver.infrastructure.service;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.poz.secureserver.infrastructure.model.UserLoginDTO;
import com.poz.secureserver.infrastructure.model.Users;
import com.poz.secureserver.infrastructure.repository.UserLoginRepository;
import com.poz.secureserver.infrastructure.repository.UserRepository;

import jakarta.persistence.EntityManager;

@Service
@Configurable
public class UserServiceImpl implements UserService {
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    
    @Autowired UserRepository userRepository;
    @Autowired UserLoginRepository userLoginRepository;
    @Autowired EntityManager entityManager;
    
    @Override
    public Users saveUser(Users user) {
        return this.userRepository.save(user);
    }

    @Override
    public List<Users> fetchUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public Optional<Users> getUser(Long id) {
        return this.userRepository.findById(id);
    }

    @Override
    public void deleteUserById(Long userID) {
        this.userRepository.deleteById(userID);
    }

    @Override
    public List<String> getUserList() {
        return this.userRepository.getUserList();
    }

    @Override
    public Boolean loginUser(String user, String password) {
        String salt = (String)entityManager.createNativeQuery("select gen_salt('bf')").getSingleResult();
        String secretPassword = (String)entityManager.createNativeQuery("select crypt(:password, :salt)")
            .setParameter("password", password)
            .setParameter("salt", salt)
            .getSingleResult();

        //  Encryption working from client to server but clear in DB for now
        Boolean resp = this.userLoginRepository.loginUser(user, password);
        logger.info("Response for loginUser: {}", resp );
        return resp;
    }

    @Override
    public Users registerUser(Users user, String clearPassword) {
        String salt = (String)entityManager.createNativeQuery("select gen_salt('bf')").getSingleResult();
        
        //  First create the user, then the user login
        Users savedUser = this.userRepository.save(user);
        Long userId = savedUser.getId();

        String secretPassword = (String)entityManager.createNativeQuery("select crypt(:password, :salt)")
            .setParameter("password", clearPassword)
            .setParameter("salt", salt)
            .getSingleResult();

        UserLoginDTO userLogin = new UserLoginDTO(savedUser.getName(), secretPassword, userId);
        UserLoginDTO login = this.userLoginRepository.save(userLogin);
        return savedUser;
    }

    public Boolean userExists(String userName) {
        return this.userRepository.exists(userName);
    }

}
