package ru.practicum.mainservice.service;

import ru.practicum.mainservice.dto.compilation.CompilationDto;
import ru.practicum.mainservice.dto.compilation.CreateCompilationDto;
import ru.practicum.mainservice.dto.compilation.UpdateCompilationDto;
import ru.practicum.mainservice.model.Compilation;

import java.util.List;

public interface CompilationService {
    CompilationDto createCompilation(CreateCompilationDto compilation);

    CompilationDto getCompilationById(int compilationId);

    Compilation getById(int compilationId);

    void deleteCompilation(int compilationId);

    CompilationDto updateCompilation(int compilationId, UpdateCompilationDto compilation);

    List<CompilationDto> getCompilations(Boolean pinned, int from, int size);
}
