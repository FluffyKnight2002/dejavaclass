package com.springboot_test.demo.repository;

import com.springboot_test.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query(value = "select r from Role r where r.name = :name")
    Optional<Role> findByRoleNameWithJPQL(@Param("name") String name);

    @Query(value = "Select * from role r where r.name = :name", nativeQuery = true)
    Optional<Role> findByRoleNameWithNative(@Param("name") String name);

    Optional<Role> findByName(String name);
}
