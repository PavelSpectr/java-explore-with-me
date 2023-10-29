package ru.practicum.mainservice.dto.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import ru.practicum.mainservice.dto.event.ShortEventDto;
import ru.practicum.mainservice.dto.user.UserDto;

import java.time.LocalDateTime;

@Data
public class CommentDto {
    private Integer id;
    private String text;
    private UserDto author;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;
    private ShortEventDto event;
}
