package ru.practicum.mainservice.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.mainservice.dto.request.RequestDto;
import ru.practicum.mainservice.model.Request;

@Component
public class RequestMapper {
    public RequestDto toDto(Request request) {
        RequestDto dto = new RequestDto();
        dto.setId(request.getId());
        dto.setRequester(request.getRequester().getId());
        dto.setCreated(request.getCreated());
        dto.setStatus(request.getStatus());
        dto.setEvent(request.getEvent().getId());
        return dto;
    }
}
