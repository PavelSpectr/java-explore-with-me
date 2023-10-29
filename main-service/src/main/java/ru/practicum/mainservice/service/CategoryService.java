package ru.practicum.mainservice.service;

import ru.practicum.mainservice.dto.category.CategoryDto;
import ru.practicum.mainservice.dto.category.CreateCategoryDto;
import ru.practicum.mainservice.model.Category;

import java.util.List;

public interface CategoryService {
    CategoryDto create(CreateCategoryDto category);

    void delete(int categoryId);

    CategoryDto getById(int categoryId);

    Category getCategoryById(int categoryId);

    CategoryDto update(int categoryId, CreateCategoryDto category);

    List<CategoryDto> getAll(int from, int size);
}
