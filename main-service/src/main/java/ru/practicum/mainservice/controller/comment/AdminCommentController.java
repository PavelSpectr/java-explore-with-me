package ru.practicum.mainservice.controller.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.mainservice.dto.comment.CommentDto;
import ru.practicum.mainservice.dto.comment.TextCommentDto;
import ru.practicum.mainservice.service.CommentService;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;

@Slf4j
@SuppressWarnings("unused")
@RestController
@RequestMapping("/admin/comments")
@RequiredArgsConstructor
@Validated
public class AdminCommentController {
    private final CommentService commentService;

    @PatchMapping("/{commentId}")
    public CommentDto editCommentAdmin(
            @RequestBody @Valid TextCommentDto dto,
            @PathVariable @PositiveOrZero Integer commentId
    ) {
        log.info("Запрос на обновление комментария commentID={} администратором", commentId);
        return commentService.editCommentAdmin(commentId, dto);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCommentAdmin(@PathVariable @PositiveOrZero Integer commentId) {
        log.info("Запрос на удаление комментария commentId={} администратором", commentId);
        commentService.deleteCommentAdmin(commentId);
    }
}
