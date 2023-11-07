package ru.practicum.mainservice.service;

import ru.practicum.mainservice.dto.user.CreateUserDto;
import ru.practicum.mainservice.dto.user.UserDto;
import ru.practicum.mainservice.model.User;

import java.util.List;

public interface UserService {
    UserDto createUser(CreateUserDto newUser);

    User getUserById(int userId);

    void deleteUserById(int userId);

    List<UserDto> getUsers(int from, int size);

    List<UserDto> getUsers(List<Integer> ids);
}
