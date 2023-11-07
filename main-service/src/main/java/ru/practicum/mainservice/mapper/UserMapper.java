package ru.practicum.mainservice.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.mainservice.dto.user.CreateUserDto;
import ru.practicum.mainservice.dto.user.UserDto;
import ru.practicum.mainservice.model.User;

@Component
public class UserMapper {
    public User fromDto(CreateUserDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        return user;
    }

    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
