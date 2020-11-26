package com.zmeevsky.administration.restservice.service;

import com.zmeevsky.administration.restservice.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();
    Role findById(int id);

    List<Role> findByRoleIdIn(List<Integer> ids);
}
