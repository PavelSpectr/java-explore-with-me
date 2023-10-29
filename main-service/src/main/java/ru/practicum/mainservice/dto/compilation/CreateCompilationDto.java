package ru.practicum.mainservice.dto.compilation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateCompilationDto {
    @UniqueElements //Проверка на уникальность элементов присутствует, Set вызовет ряд проблем в работе с репозиторием
    private List<Integer> events = Collections.emptyList();
    private Boolean pinned = false;
    @NotBlank
    @Size(min = 1, max = 50)
    private String title;
}
