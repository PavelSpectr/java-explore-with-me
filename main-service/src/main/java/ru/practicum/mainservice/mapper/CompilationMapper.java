package ru.practicum.mainservice.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.mainservice.dto.compilation.CompilationDto;
import ru.practicum.mainservice.dto.compilation.CreateCompilationDto;
import ru.practicum.mainservice.dto.compilation.UpdateCompilationDto;
import ru.practicum.mainservice.model.Compilation;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CompilationMapper {

    private final EventMapper eventMapper;

    public Compilation fromDto(CreateCompilationDto dto) {
        Compilation compilation = new Compilation();
        compilation.setPinned(dto.getPinned());
        compilation.setTitle(dto.getTitle());
        return compilation;
    }

    public CompilationDto toDto(Compilation compilation) {
        CompilationDto dto = new CompilationDto();
        dto.setId(compilation.getId());
        dto.setEvents(compilation.getEvents().stream().map(eventMapper::toShortDto).collect(Collectors.toList()));
        dto.setPinned(compilation.isPinned());
        dto.setTitle(compilation.getTitle());
        return dto;
    }

    public Compilation fromDto(UpdateCompilationDto dto) {
        Compilation compilation = new Compilation();
        compilation.setPinned(dto.getPinned());
        compilation.setTitle(dto.getTitle());
        return compilation;
    }
}
