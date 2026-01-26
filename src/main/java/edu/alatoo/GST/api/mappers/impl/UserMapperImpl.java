package edu.alatoo.GST.api.mappers.impl;

import org.springframework.stereotype.Component;

import edu.alatoo.GST.api.dto.UserDto;
import edu.alatoo.GST.api.mappers.UserMapper;
import edu.alatoo.GST.data.models.User;

@Component
public class UserMapperImpl implements UserMapper{
    @Override
    public UserDto toDto(User user){
        if(user == null) return null;

        UserDto userDto = UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();

        return userDto;
    }

    @Override
    public User toEntity(UserDto userDto){
        if(userDto == null) return null;

        User user = User.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .role(userDto.getRole())
                .build();

        return user;
    }
}
