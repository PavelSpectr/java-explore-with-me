package ru.practicum.mainservice.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.mainservice.dto.event.CreateEventDto;
import ru.practicum.mainservice.dto.event.EventDto;
import ru.practicum.mainservice.dto.event.ShortEventDto;
import ru.practicum.mainservice.dto.event.UpdateEventDto;
import ru.practicum.mainservice.model.Event;

@Component
@RequiredArgsConstructor
public class EventMapper {

    private final LocationMapper locationMapper;
    private final CategoryMapper categoryMapper;
    private final UserMapper userMapper;

    public Event toModel(CreateEventDto dto) {
        Event event = new Event();
        event.setAnnotation(dto.getAnnotation());
        event.setDescription(dto.getDescription());
        event.setEventDate(dto.getEventDate());
        event.setLocation(locationMapper.fromDto(dto.getLocation()));
        event.setPaid(dto.getPaid());
        event.setParticipantLimit(dto.getParticipantLimit());
        event.setRequestModeration(dto.getRequestModeration());
        event.setTitle(dto.getTitle());
        return event;
    }

    public Event toModel(UpdateEventDto dto) {
        Event event = new Event();
        event.setAnnotation(dto.getAnnotation());
        event.setDescription(dto.getDescription());
        event.setEventDate(dto.getEventDate());
        event.setLocation(locationMapper.fromDto(dto.getLocation()));
        event.setPaid(dto.getPaid());
        event.setParticipantLimit(dto.getParticipantLimit());
        event.setRequestModeration(dto.getRequestModeration());
        event.setTitle(dto.getTitle());
        return event;
    }

    public EventDto toDto(Event event, int views, int confirmedRequests) {
        EventDto dto = new EventDto();
        dto.setId(event.getId());
        dto.setAnnotation(event.getAnnotation());
        dto.setCategory(categoryMapper.toDto(event.getCategory()));
        dto.setDescription(event.getDescription());
        dto.setEventDate(event.getEventDate());
        dto.setCreatedOn(event.getCreateDate());
        dto.setInitiator(userMapper.toDto(event.getInitiator()));
        dto.setLocation(locationMapper.toModel(event.getLocation()));
        dto.setPaid(event.getPaid());
        dto.setParticipantLimit(event.getParticipantLimit());
        dto.setRequestModeration(event.getRequestModeration());
        dto.setTitle(event.getTitle());
        dto.setState(event.getState());
        dto.setViews(views);
        dto.setPublishedOn(event.getPublishedDate());
        dto.setConfirmedRequests(confirmedRequests);
        return dto;
    }

    public ShortEventDto toShortDto(Event event) {
        ShortEventDto dto = new ShortEventDto();
        dto.setId(event.getId());
        dto.setAnnotation(event.getAnnotation());
        dto.setCategory(categoryMapper.toDto(event.getCategory()));
        dto.setEventDate(event.getEventDate());
        dto.setInitiator(userMapper.toDto(event.getInitiator()));
        dto.setPaid(event.getPaid());
        dto.setTitle(event.getTitle());
        return dto;
    }
}
