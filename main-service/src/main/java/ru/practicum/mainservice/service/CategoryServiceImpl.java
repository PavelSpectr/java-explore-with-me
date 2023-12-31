package ru.practicum.mainservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.mainservice.dto.category.CategoryDto;
import ru.practicum.mainservice.dto.category.CreateCategoryDto;
import ru.practicum.mainservice.exception.APIException;
import ru.practicum.mainservice.mapper.CategoryMapper;
import ru.practicum.mainservice.model.Category;
import ru.practicum.mainservice.repository.CategoryRepository;
import ru.practicum.mainservice.repository.EventRepository;
import ru.practicum.mainservice.util.OffsetBasedPageRequest;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService { //Вызов идет не по репозиторию, а через маппер по причине рекурсии вызовов

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final EventRepository eventRepository;

    @Override
    @Transactional
    public CategoryDto create(CreateCategoryDto category) {
        Category newCategory = new Category();
        newCategory.setName(category.getName());
        return categoryMapper.toDto(categoryRepository.save(newCategory));
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDto getById(int categoryId) {
        Category category = getCategoryById(categoryId);
        return categoryMapper.toDto(category);
    }

    @Override
    @Transactional(readOnly = true)
    public Category getCategoryById(int categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new APIException(HttpStatus.NOT_FOUND, String.format(
                        "Category with id=%s was not found",
                        categoryId
                ), "The required object was not found."));
    }

    @Override
    @Transactional
    public void delete(int categoryId) {
        Category category = getCategoryById(categoryId);
        if (category != null) {
            boolean hasEventsInCategory = eventRepository.findAll().contains(category);
            if (!hasEventsInCategory) {
                categoryRepository.delete(category);
            }
        }
    }

    @Override
    @Transactional
    public CategoryDto update(int categoryId, CreateCategoryDto category) {
        Category fromDb = getCategoryById(categoryId);
        fromDb.setName(category.getName());
        return categoryMapper.toDto(fromDb);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDto> getAll(int from, int size) {
        Pageable pageable = new OffsetBasedPageRequest(from, size);
        return categoryRepository.findAll(pageable).getContent().stream()
                .map(categoryMapper::toDto).collect(Collectors.toList());
    }
}
