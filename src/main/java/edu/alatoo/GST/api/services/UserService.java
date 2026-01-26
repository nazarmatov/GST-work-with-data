package edu.alatoo.GST.api.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.alatoo.GST.api.dto.UserDto;
import edu.alatoo.GST.api.mappers.UserMapper;
import edu.alatoo.GST.data.models.User;
import edu.alatoo.GST.data.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import edu.alatoo.GST.api.exceptions.BadRequestException;
import edu.alatoo.GST.api.exceptions.ResourceNotFoundException;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    
    @Transactional(readOnly = true)
    public Page<UserDto> getAllUsers(Pageable pageable){
        log.info("getting all users");
        return userRepository.findAll(pageable).map(userMapper::toDto);
    }

    @Transactional(readOnly = true)
    public UserDto getUserById(Long id){
        log.info("getting user by id: {}", id);
        return userMapper.toDto(userRepository.findById(id).orElseThrow(
            () -> {
                log.error("user with id: {} not found", id);
                return new ResourceNotFoundException("user with id: " + id + " not found");
            }  
        ));
    }

    public UserDto createUser(UserDto userDto){
        log.info("creating user: {}", userDto);

        if (!userRepository.existsByEmail(userDto.getEmail())) {
            log.error("User with ");
            throw new BadRequestException("User with email: " + userDto.getEmail() + " already exists");
        }

        try {
            User user = userMapper.toEntity(userDto);
            User savedUser = userRepository.save(user);
            return userMapper.toDto(savedUser);
        } catch (Exception e) {
            log.error("error with saving user");
            throw new BadRequestException("Error creating user: " + e.getMessage());
        }
    }

    public UserDto updateUser(Long id, UserDto userDto){
        log.info("updating user with id: {}", id);
        User existingUser = userRepository.findById(id).orElseThrow(
            () -> {
                throw new ResourceNotFoundException("User with id: " + id + " not found");
            });

        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setRole(userDto.getRole());

        User updatedUser = userRepository.save(existingUser);
        return userMapper.toDto(updatedUser);
    }

    public void deleteUser(Long id){
        log.info("deleting user with id: {}", id);
        if (!userRepository.existsById(id)){
            throw new ResourceNotFoundException("User no found");
        }
        userRepository.deleteById(id);
    }

}
