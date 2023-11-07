package ru.practicum.mainservice.dto.compilation;

import lombok.Data;
import ru.practicum.mainservice.dto.event.ShortEventDto;

import java.util.List;

@Data
public class CompilationDto {
    private int id;
    private List<ShortEventDto> events;
    private boolean pinned;
    private String title;
}
