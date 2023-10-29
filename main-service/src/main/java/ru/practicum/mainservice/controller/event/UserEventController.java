package ru.practicum.mainservice.controller.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainservice.dto.event.CreateEventDto;
import ru.practicum.mainservice.dto.event.EventDto;
import ru.practicum.mainservice.dto.event.ShortEventDto;
import ru.practicum.mainservice.dto.event.UpdateEventDto;
import ru.practicum.mainservice.dto.filter.PageFilterDto;
import ru.practicum.mainservice.dto.request.RequestDto;
import ru.practicum.mainservice.dto.request.UpdateRequestDto;
import ru.practicum.mainservice.dto.request.UpdateRequestResultDto;
import ru.practicum.mainservice.service.EventService;
import ru.practicum.mainservice.service.RequestService;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@SuppressWarnings("unused")
@RestController
@RequestMapping("/users/{userId}/events")
@RequiredArgsConstructor
@Validated
public class UserEventController {

    private final EventService eventService;
    private final RequestService requestService;

    @GetMapping
    public List<ShortEventDto> getEvents(@PathVariable @PositiveOrZero int userId, @Valid PageFilterDto pageableData) {
        log.info("Запрос на получение событий пользователя id={} pageable={}", userId, pageableData);
        List<ShortEventDto> events = eventService.getAll(userId, pageableData.getFrom(), pageableData.getSize());
        log.info("Найдено {} событий для id={} pageable={}", events.size(), userId, pageableData);
        return events;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventDto createEvent(@PathVariable @PositiveOrZero int userId, @RequestBody @Valid CreateEventDto dto) {
        log.info("Получен запрос на создание мероприятия userId={} data={}", userId, dto);
        EventDto event = eventService.createEvent(userId, dto);
        log.info("Мероприятие {} успешно создано", event);
        return event;
    }

    @GetMapping("/{eventId}")
    public EventDto getUserEventById(@PathVariable @PositiveOrZero int userId, @PathVariable @PositiveOrZero int eventId) {
        log.info("Запрос на получение события eventId={}, userId={}", eventId, userId);
        EventDto event = eventService.getByInitiatorAndId(userId, eventId);
        log.info("Событие по запросу eventId={}, userId={} = {}", eventId, userId, event);
        return event;
    }

    @PatchMapping("/{eventId}")
    public EventDto updateUserEvent(
            @PathVariable @PositiveOrZero int userId,
            @PathVariable @PositiveOrZero int eventId,
            @RequestBody @Valid UpdateEventDto dto
    ) {
        log.info("Запрос на редактирование события eventId={}, userId={}, data={}", eventId, userId, dto);
        EventDto event = eventService.updateEvent(userId, eventId, dto);
        log.info("Событие eventId={}, userId={} успешно отредактировано data={}", eventId, userId, event);
        return event;
    }

    @GetMapping("/{eventId}/requests")
    public List<RequestDto> getEventRequests(
            @PathVariable @PositiveOrZero int userId,
            @PathVariable @PositiveOrZero int eventId
    ) {
        log.info("Получен запрос на получение списка заявок userId={}, eventId={}", userId, eventId);
        List<RequestDto> requests = requestService.getUserEventRequests(userId, eventId);
        log.info("Найдено запросов {} userId={}, eventId={}", requests.size(), userId, eventId);
        return requests;
    }

    @PatchMapping("/{eventId}/requests")
    public UpdateRequestResultDto updateEventRequests(
            @PathVariable @PositiveOrZero int userId,
            @PathVariable @PositiveOrZero int eventId,
            @RequestBody @Valid UpdateRequestDto dto
    ) {
        log.info("Получен запрос на обновление заявок userId={}, eventId={}, data={}", userId, eventId, dto);
        UpdateRequestResultDto result = requestService.updateRequests(userId, eventId, dto);
        log.info("Результат обновления заявок {}", result);
        return result;
    }
}
