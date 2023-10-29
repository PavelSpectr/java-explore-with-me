package ru.practicum.mainservice.service;

import ru.practicum.mainservice.dto.comment.CommentDto;
import ru.practicum.mainservice.dto.comment.CreateCommentDto;
import ru.practicum.mainservice.dto.filter.PageFilterDto;
import ru.practicum.mainservice.model.Comment;

import java.util.List;

public interface CommentService {
    CommentDto addComment(CreateCommentDto dto, Integer userId, Integer eventId);

    CommentDto editComment(CreateCommentDto dto, Integer userId, Integer commentId);

    CommentDto getById(Integer userId, Integer commentId);

    Comment getCommentById(Integer commentId);

    List<CommentDto> getAllUserComments(Integer userId, Integer from, Integer size);

    void deleteCommentById(Integer userId, Integer commentId);

    CommentDto editCommentAdmin(Integer commentId, CreateCommentDto dto);

    void deleteCommentAdmin(Integer commentId);

    List<CommentDto> getAllCommentsByEvent(Integer eventId, PageFilterDto pageFilter);
}