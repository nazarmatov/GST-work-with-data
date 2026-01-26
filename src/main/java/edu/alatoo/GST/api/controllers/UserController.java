package edu.alatoo.GST.api.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.alatoo.GST.api.dto.UserDto;
import edu.alatoo.GST.api.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Validated
@Slf4j
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        log.info("get User with id {}", id);
        UserDto userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping
    public ResponseEntity<Page<UserDto>> getAllUsers(Pageable pageable){
        log.info("get all Users");
        Page<UserDto> users = userService.getAllUsers(pageable);
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        log.info("create User {}", userDto);
        UserDto craetedUser = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(craetedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @Valid @RequestBody UserDto userDto){
        log.info("update user with id {}", id);
        UserDto updatedEducato = userService.updateUser(id, userDto);
        return ResponseEntity.ok(updatedEducato);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        log.info("delete User with id {}", id);
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
