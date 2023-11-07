package ru.practicum.mainservice.controller.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainservice.dto.event.EventDto;
import ru.practicum.mainservice.dto.event.UpdateEventDto;
import ru.practicum.mainservice.dto.filter.AdminEventFilterDto;
import ru.practicum.mainservice.service.EventService;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@SuppressWarnings("unused")
@RestController
@RequestMapping("/admin/events")
@RequiredArgsConstructor
@Validated
public class AdminEventController {
    private final EventService eventService;

    @GetMapping
    public List<EventDto> getEvents(@Valid AdminEventFilterDto eventFilter) {
        log.info("Поиск событий по фильтру {}, from={}, size={}", eventFilter, eventFilter.getFrom(), eventFilter.getSize());
        List<EventDto> events = eventService.findEvents(eventFilter);
        log.info("Найдено {} событий", events.size());
        return events;
    }

    @PatchMapping("/{eventId}")
    public EventDto editEventById(@PathVariable @PositiveOrZero int eventId, @RequestBody @Valid UpdateEventDto dto) {
        log.info("Запрос на редактирование события eventId={}, data={}", eventId, dto);
        return eventService.updateAdminEvent(eventId, dto);
    }
}
