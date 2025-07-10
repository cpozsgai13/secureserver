package com.poz.secureserver.infrastructure.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poz.secureserver.infrastructure.model.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

    @Query(value = "select name from users", nativeQuery=true)
    List<String> getUserList();

    @Query(value = "select exists(select 1 from users where name = :name)", nativeQuery=true)
    public Boolean exists(@Param("name") String name);
}
