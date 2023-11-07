package ru.practicum.mainservice.controller.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.mainservice.dto.event.EventDto;
import ru.practicum.mainservice.dto.event.ShortEventDto;
import ru.practicum.mainservice.dto.filter.EventFilterDto;
import ru.practicum.mainservice.service.EventService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@SuppressWarnings("unused")
@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@Validated
public class EventController {

    private final EventService eventService;

    @GetMapping
    public List<ShortEventDto> getEvents(@Valid EventFilterDto eventFilter, HttpServletRequest request) {
        log.info("Поиск событий по фильтру {}", eventFilter);
        eventService.addStatistic(request);
        List<ShortEventDto> events = eventService.findEvents(eventFilter);
        log.info("Найдено {} событий", events.size());
        return events;
    }

    @GetMapping("/{eventId}")
    public EventDto getEventById(@PathVariable @PositiveOrZero int eventId, HttpServletRequest request) {
        log.info("Получение публичного события по id={}", eventId);
        eventService.addStatistic(request);
        EventDto event = eventService.getPublishedEventById(eventId);
        log.info("Найдено событие {}", event);
        return event;
    }
}
