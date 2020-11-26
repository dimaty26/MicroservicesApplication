package com.zmeevsky.administration.restservice.service;

import com.zmeevsky.administration.restservice.entity.Role;
import com.zmeevsky.administration.restservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(int id) {
        return roleRepository.getOne(id);
    }

    @Override
    public List<Role> findByRoleIdIn(List<Integer> ids) {
        return roleRepository.findByRoleIdIn(ids);
    }
}
