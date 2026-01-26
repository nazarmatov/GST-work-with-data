package edu.alatoo.GST.api.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.alatoo.GST.api.dto.CommentDto;
import edu.alatoo.GST.api.services.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Validated
@Slf4j
@RequestMapping("/api/v1/educators/{educatorId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable Long id, @PathVariable Long educatorId){
        log.info("get comment with id {}", id);
        CommentDto commentDto = commentService.getCommentByIdAndEducator(id, educatorId);
        return ResponseEntity.ok(commentDto);
    }

    @GetMapping
    public ResponseEntity<Page<CommentDto>> getAllCommentByEducator(@PathVariable Long educatorId, Pageable pageable){
        log.info("fet all educators comment");
        Page<CommentDto> comments = commentService.getAllCommentsByEducator(educatorId, pageable);
        return ResponseEntity.ok(comments);
    }

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentDto commentDto, @PathVariable Long educatorId, @PathVariable Long userId){
        log.info("create comment {}", commentDto);
        CommentDto createdComment = commentService.createComment(commentDto, educatorId, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long id, @Valid @RequestBody CommentDto commentDto, @PathVariable Long educatorId){
        log.info("update comment with id {}", id);
        CommentDto updatedComment = commentService.updateComment(id, commentDto,educatorId);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id, @PathVariable Long educatorId){
        log.info("delete comment with id {}",id);
        commentService.deleteComment(id,educatorId);
        return ResponseEntity.noContent().build();
    }

}
