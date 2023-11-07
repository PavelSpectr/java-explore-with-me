package ru.practicum.mainservice.dto.category;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CreateCategoryDto {
    @NotBlank
    @Size(min = 1, max = 50)
    private String name;
}
