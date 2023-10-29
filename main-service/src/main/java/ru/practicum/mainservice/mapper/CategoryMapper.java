package ru.practicum.mainservice.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.mainservice.dto.category.CategoryDto;
import ru.practicum.mainservice.dto.category.CreateCategoryDto;
import ru.practicum.mainservice.model.Category;

@Component
public class CategoryMapper {
    public CategoryDto toDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }

    public Category toModel(CreateCategoryDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }
}
