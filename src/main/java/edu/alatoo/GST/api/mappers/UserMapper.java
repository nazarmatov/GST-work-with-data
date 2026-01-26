package edu.alatoo.GST.api.mappers;

import edu.alatoo.GST.api.dto.UserDto;
import edu.alatoo.GST.data.models.User;

public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
}
