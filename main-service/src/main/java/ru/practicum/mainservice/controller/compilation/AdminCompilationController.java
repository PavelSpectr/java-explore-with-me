package ru.practicum.mainservice.controller.compilation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainservice.dto.compilation.CompilationDto;
import ru.practicum.mainservice.dto.compilation.CreateCompilationDto;
import ru.practicum.mainservice.dto.compilation.UpdateCompilationDto;
import ru.practicum.mainservice.service.CompilationService;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;

@Slf4j
@SuppressWarnings("unused")
@RestController
@RequestMapping("/admin/compilations")
@RequiredArgsConstructor
@Validated
public class AdminCompilationController {

    private final CompilationService compilationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompilationDto createCompilation(@RequestBody @Valid CreateCompilationDto dto) {
        log.info("Получен запрос на создание подборки data={}", dto);
        CompilationDto compilation = compilationService.createCompilation(dto);
        log.info("Подборка успешно создана data={}", compilation);
        return compilation;
    }

    @DeleteMapping("/{compilationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompilation(@PathVariable @PositiveOrZero int compilationId) {
        compilationService.deleteCompilation(compilationId);
    }

    @PatchMapping("/{compId}")
    public CompilationDto updateCompilation(
            @PathVariable @PositiveOrZero int compId,
            @RequestBody(required = false) @Valid UpdateCompilationDto dto
    ) {
        log.info("Получен запрос на изменение подборки data={}", dto);
        CompilationDto compilation = compilationService.updateCompilation(compId, dto);
        log.info("Подборка успешно изменена data={}", compilation);
        return compilation;
    }
}
