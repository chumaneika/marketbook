package org.magasbook.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.magasbook.dto.userdto.UserCreateDTO;
import org.magasbook.dto.userdto.UserUpdateDTO;
import org.magasbook.models.UserEntity;
import org.magasbook.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserEntity> createUser(@Valid @RequestBody UserCreateDTO dto) {
        UserEntity createdUser = userService.createUser(dto);
        return ResponseEntity.ok(createdUser);
    }

    @PutMapping("/assign-role/{adminId}/{userId}")
    public ResponseEntity<Void> assignRole(@PathVariable("adminId") Long adminId, @PathVariable("userId") Long userId) throws AccessDeniedException {
        userService.assignRole(adminId, userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update-user")
    public ResponseEntity<Void> updateUser(@Valid @RequestBody UserUpdateDTO dto) {
        userService.updateUser(dto);
        return ResponseEntity.ok().build();
    }
}
