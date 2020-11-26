package com.zmeevsky.administration.restservice.repository;

import com.zmeevsky.administration.restservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query("select r from Role r where r.id in :ids")
    List<Role> findByRoleIdIn(@Param("ids") List<Integer> ids);
}
