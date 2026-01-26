package edu.alatoo.GST.api.mappers.impl;

import org.springframework.stereotype.Component;

import edu.alatoo.GST.api.dto.CommentDto;
import edu.alatoo.GST.api.mappers.CommentMapper;
import edu.alatoo.GST.data.models.Comment;
import edu.alatoo.GST.data.models.Educator;
import edu.alatoo.GST.data.models.User;

@Component
public class CommentMapperImpl implements CommentMapper {
    @Override
    public CommentDto toDto(Comment comment){
        if(comment == null) return null;

        CommentDto commentDto = CommentDto.builder()
                .id(comment.getId())
                .text(comment.getText())
                .createdAt(comment.getCreatedAt())
                .educatorId(comment.getEducator().getId())
                .userId(comment.getUser().getId())
                .build();

        return commentDto;
    }

    @Override
    public Comment toEntity(CommentDto commentDto, Educator educator, User user){
        if(commentDto == null) return null;

        Comment comment = Comment.builder()
                .id(commentDto.getId())
                .createdAt(commentDto.getCreatedAt())
                .text(commentDto.getText())
                .educator(educator)
                .user(user)
                .build();
                
        return comment;
    }
}