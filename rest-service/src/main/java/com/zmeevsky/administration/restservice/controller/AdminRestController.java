package com.zmeevsky.administration.restservice.controller;

import com.zmeevsky.administration.restservice.dto.SaveUserRequest;
import com.zmeevsky.administration.restservice.dto.SaveUserResponse;
import com.zmeevsky.administration.restservice.dto.UpdateUserRequest;
import com.zmeevsky.administration.restservice.dto.UpdateUserResponse;
import com.zmeevsky.administration.restservice.entity.Role;
import com.zmeevsky.administration.restservice.entity.User;
import com.zmeevsky.administration.restservice.service.RoleService;
import com.zmeevsky.administration.restservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable int userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @PostMapping()
    public ResponseEntity<SaveUserResponse> addUser(@Valid @RequestBody SaveUserRequest user) {

        userService.saveUser(
                new User(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getRoles()));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<UpdateUserResponse> updateUser(@RequestBody UpdateUserRequest user) {

        List<Integer> roleIds = new ArrayList<>();
        user.getRoles().forEach(w -> roleIds.add(w.getId()));

        Set<Role> userRoles = new HashSet<>(roleService.findByRoleIdIn(roleIds));

        userService.updateUser(
                new User(user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getPassword(),
                        userRoles));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable int userId) {

        userService.deleteUser(userId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
