package ru.practicum.mainservice.service;

import ru.practicum.mainservice.dto.event.CreateEventDto;
import ru.practicum.mainservice.dto.event.EventDto;
import ru.practicum.mainservice.dto.event.ShortEventDto;
import ru.practicum.mainservice.dto.event.UpdateEventDto;
import ru.practicum.mainservice.dto.filter.AdminEventFilterDto;
import ru.practicum.mainservice.dto.filter.EventFilterDto;
import ru.practicum.mainservice.model.Event;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface EventService {
    Event getEventById(int eventId);

    EventDto createEvent(int userId, CreateEventDto dto);

    EventDto updateEvent(int userId, int eventId, UpdateEventDto dto);

    EventDto updateAdminEvent(int eventId, UpdateEventDto dto);

    List<ShortEventDto> getAll(int userId, int from, int size);

    EventDto getByInitiatorAndId(int userId, int eventId);

    EventDto getPublishedEventById(int eventId);

    List<ShortEventDto> findEvents(EventFilterDto eventFilter);

    List<EventDto> findEvents(AdminEventFilterDto eventFilter);

    List<EventDto> findAllByIds(List<Integer> eventIds);

    List<Event> findAllEventByIds(List<Integer> eventIds);

    void addStatistic(HttpServletRequest request);

    Map<Integer, Integer> getViews(List<Event> events);

    Map<Integer, Integer> getConfirmedRequests(List<Event> events);
}
