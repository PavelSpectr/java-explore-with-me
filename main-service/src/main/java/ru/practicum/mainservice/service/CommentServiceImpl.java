package ru.practicum.mainservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.mainservice.dto.comment.CommentDto;
import ru.practicum.mainservice.dto.comment.TextCommentDto;
import ru.practicum.mainservice.dto.filter.PageFilterDto;
import ru.practicum.mainservice.enums.EventState;
import ru.practicum.mainservice.exception.APIException;
import ru.practicum.mainservice.mapper.CommentMapper;
import ru.practicum.mainservice.model.Comment;
import ru.practicum.mainservice.model.Event;
import ru.practicum.mainservice.model.User;
import ru.practicum.mainservice.repository.CommentRepository;
import ru.practicum.mainservice.util.OffsetBasedPageRequest;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final EventService eventService;
    private final UserService userService;
    private final CommentMapper commentMapper;

    @Override
    @Transactional
    public CommentDto addComment(TextCommentDto dto, Integer userId, Integer eventId) {
        Event event = eventService.getEventById(eventId);
        User author = userService.getUserById(userId);
        if (EventState.PUBLISHED.equals(event.getState()))
            throw new APIException(
                    HttpStatus.CONFLICT,
                    "Only publish event can been commented",
                    "For the requested operation the conditions are not met."
            );
        Comment comment = new Comment();
        comment.setAuthor(author);
        comment.setEvent(event);
        comment.setText(dto.getText());
        return commentMapper.toDto(commentRepository.save(comment));
    }

    @Override
    @Transactional
    public CommentDto editComment(TextCommentDto dto, Integer userId, Integer commentId) {
        Comment commentFromDB = getCommentById(commentId);
        User author = userService.getUserById(userId);
        if (!commentFromDB.getAuthor().getId().equals(author.getId()))
            throw new APIException(
                    HttpStatus.CONFLICT,
                    "Only comment owner or admin can been edit comment",
                    "For the requested operation the conditions are not met."
            );
        commentFromDB.setText(dto.getText());
        return commentMapper.toDto(commentFromDB);
    }

    @Override
    @Transactional(readOnly = true)
    public Comment getCommentById(Integer commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new APIException(HttpStatus.NOT_FOUND, String.format(
                        "Comment with id=%s was not found",
                        commentId
                ), "The required object was not found."));
    }

    @Override
    @Transactional(readOnly = true)
    public CommentDto getById(Integer userId, Integer commentId) {
        userService.getUserById(userId);
        return commentMapper.toDto(getCommentById(commentId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDto> getAllUserComments(Integer userId, Integer from, Integer size) {
        User author = userService.getUserById(userId);
        Pageable pageable = new OffsetBasedPageRequest(from, size);
        return commentRepository.findAllByAuthor(author, pageable).stream().map(commentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteCommentById(Integer userId, Integer commentId) {
        Comment comment = getCommentById(commentId);
        User author = userService.getUserById(userId);
        if (!comment.getAuthor().getId().equals(author.getId()))
            throw new APIException(
                    HttpStatus.CONFLICT,
                    "Only comment owner or admin can been delete comment",
                    "For the requested operation the conditions are not met."
            );
        commentRepository.delete(comment);
    }

    @Override
    @Transactional
    public CommentDto editCommentAdmin(Integer commentId, TextCommentDto dto) {
        Comment commentFromDB = getCommentById(commentId);
        commentFromDB.setText(dto.getText());
        return commentMapper.toDto(commentFromDB);
    }

    @Override
    @Transactional
    public void deleteCommentAdmin(Integer commentId) {
        Comment comment = getCommentById(commentId);
        commentRepository.delete(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentDto> getAllCommentsByEvent(Integer eventId, PageFilterDto pageFilter) {
        Event event = eventService.getEventById(eventId);
        Pageable pageable = new OffsetBasedPageRequest(pageFilter.getFrom(), pageFilter.getSize());
        return commentRepository.findAllByEvent(event, pageable).stream().map(commentMapper::toDto)
                .collect(Collectors.toList());
    }
}