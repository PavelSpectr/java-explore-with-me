package ru.practicum.mainservice.controller.compilation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainservice.dto.compilation.CompilationDto;
import ru.practicum.mainservice.dto.filter.PageFilterDto;
import ru.practicum.mainservice.service.CompilationService;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@SuppressWarnings("unused")
@RestController
@RequestMapping("/compilations")
@RequiredArgsConstructor
@Validated
public class CompilationController {
    private final CompilationService compilationService;

    @GetMapping
    public List<CompilationDto> getCompilations(
            @Valid PageFilterDto pageFilter,
            @RequestParam(required = false) Boolean pinned
    ) {
        log.info("Запрос на список подборок pinned={} page={}", pinned, pageFilter);
        List<CompilationDto> compilations = compilationService.getCompilations(
                pinned,
                pageFilter.getFrom(),
                pageFilter.getSize()
        );
        log.info("Найдено подборок {}", compilations.size());
        return compilations;
    }

    @GetMapping("/{compId}")
    public CompilationDto getCompilationById(@PathVariable @PositiveOrZero int compId) {
        log.info("Запрос на получение подборки compId={}", compId);
        CompilationDto compilation = compilationService.getCompilationById(compId);
        log.info("Найдена подборка {}", compilation);
        return compilation;
    }
}
