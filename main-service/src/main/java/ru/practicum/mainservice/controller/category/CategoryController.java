package ru.practicum.mainservice.controller.category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.mainservice.dto.category.CategoryDto;
import ru.practicum.mainservice.dto.filter.PageFilterDto;
import ru.practicum.mainservice.service.CategoryService;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@SuppressWarnings("unused")
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Validated
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> getCategories(@Valid PageFilterDto pageableData) {
        log.info("Получен запрос на получение категорий page={}", pageableData);
        List<CategoryDto> categories = categoryService.getAll(pageableData.getFrom(), pageableData.getSize());
        log.info("Найдено {} категорий", categories.size());
        return categories;
    }

    @GetMapping("/{categoryId}")
    public CategoryDto getCategories(@PathVariable @PositiveOrZero int categoryId) {
        log.info("Получен запрос на получение категории categoryId={}", categoryId);
        CategoryDto category = categoryService.getById(categoryId);
        log.info("Получена категория {}", category);
        return category;
    }
}
