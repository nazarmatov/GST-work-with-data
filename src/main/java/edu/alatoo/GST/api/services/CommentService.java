package edu.alatoo.GST.api.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.alatoo.GST.api.dto.CommentDto;
import edu.alatoo.GST.api.exceptions.BadRequestException;
import edu.alatoo.GST.api.exceptions.ResourceNotFoundException;
import edu.alatoo.GST.api.mappers.CommentMapper;
import edu.alatoo.GST.data.models.Comment;
import edu.alatoo.GST.data.models.User;
import edu.alatoo.GST.data.models.Educator;
import edu.alatoo.GST.data.repositories.CommentRepository;
import edu.alatoo.GST.data.repositories.EducatorRepository;
import edu.alatoo.GST.data.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final EducatorRepository educatorRepository;
    private final CommentMapper commentMapper;

    public CommentDto getCommentByIdAndEducator(Long id, Long educatorId){
        log.info("get comment with id {}", id);
        Educator educator = educatorRepository.findById(educatorId).orElseThrow(() -> new ResourceNotFoundException("can not found educator"));
        CommentDto commentDto = commentMapper.toDto(commentRepository.findByIdAndEducator(id, educator).orElseThrow(() -> new ResourceNotFoundException("can not found comment")));
        return commentDto;
    }

    public Page<CommentDto> getAllCommentsByEducator(Long educatorId, Pageable pageable){
        log.info("find all comments of educator with id {}",educatorId);
        Educator educator = educatorRepository.findById(educatorId).orElseThrow(() -> new ResourceNotFoundException("educator not found"));
        Page<CommentDto> comments = commentRepository.findAllByEducator(educator, pageable).map(commentMapper::toDto);
        return comments;
    }

    public CommentDto createComment(CommentDto commentDto, Long educatorId, Long userId) {
        log.info("creating comment: {}", commentDto);
        Educator educator = educatorRepository.findById(educatorId).orElseThrow(
                () -> new ResourceNotFoundException("can not find educator")
        );
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("can not find user")
        );
        Comment comment = commentMapper.toEntity(commentDto, educator, user);
        Comment savedComment = commentRepository.save(comment);
        return commentMapper.toDto(savedComment);
    }

    public CommentDto updateComment(Long id,CommentDto commentDto,Long educatorId) {
        log.info("updating comment: {}", commentDto);
        Educator educator = educatorRepository.findById(educatorId).orElseThrow(() -> new ResourceNotFoundException("edducator not found"));
        Comment comment = commentRepository.findByIdAndEducator(id, educator).orElseThrow(
                () -> new ResourceNotFoundException("can not find comment")
        );
        comment.setText(commentDto.getText());
        Comment updatedComment = commentRepository.save(comment);
        return commentMapper.toDto(updatedComment);
    }

    public void deleteComment(Long id, Long educatorId) {
        log.info("deleting comment with id: {}", id);
        Educator educator = educatorRepository.findById(educatorId).orElseThrow(() -> new ResourceNotFoundException("can not find educator for delete comment"));
        if (!commentRepository.existsByIdAndEducator(id, educator)) {
            throw new BadRequestException("can not find comment");
        }
        commentRepository.deleteByIdAndEducator(id, educator);
    }
}
