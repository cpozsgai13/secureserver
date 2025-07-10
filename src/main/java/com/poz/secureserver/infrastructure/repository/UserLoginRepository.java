package com.poz.secureserver.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poz.secureserver.infrastructure.model.UserLoginDTO;

public interface UserLoginRepository extends JpaRepository<UserLoginDTO, Long> {

    @Query(value="select count(*) = 1 from user_login u where u.name=:name and u.password=:password", nativeQuery=true)
    Boolean loginUser(@Param("name") String name, @Param("password") String password);
}
