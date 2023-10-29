package ru.practicum.mainservice.dto.filter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.practicum.mainservice.enums.EventState;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class AdminEventFilterDto extends PageFilterDto {
    private List<Integer> users;
    private List<EventState> states;
    private List<Integer> categories;
    private LocalDateTime rangeStart;
    private LocalDateTime rangeEnd;
}
