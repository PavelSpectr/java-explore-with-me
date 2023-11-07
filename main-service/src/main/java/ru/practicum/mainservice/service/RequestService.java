package ru.practicum.mainservice.service;

import ru.practicum.mainservice.dto.request.RequestDto;
import ru.practicum.mainservice.dto.request.UpdateRequestDto;
import ru.practicum.mainservice.dto.request.UpdateRequestResultDto;
import ru.practicum.mainservice.model.Request;

import java.util.List;

public interface RequestService {
    Request getRequestById(int requestId);

    RequestDto createRequest(int userId, int eventId);

    List<RequestDto> getUserRequests(int userId);

    RequestDto cancelRequest(int userId, int requestId);

    List<RequestDto> getUserEventRequests(int userId, int eventId);

    UpdateRequestResultDto updateRequests(int userId, int eventId, UpdateRequestDto dto);
}
