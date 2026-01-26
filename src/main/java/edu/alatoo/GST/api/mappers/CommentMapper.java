package edu.alatoo.GST.api.mappers;

import edu.alatoo.GST.api.dto.CommentDto;
import edu.alatoo.GST.data.models.Comment;
import edu.alatoo.GST.data.models.Educator;
import edu.alatoo.GST.data.models.User;

public interface CommentMapper {
    CommentDto toDto(Comment comment);
    Comment toEntity(CommentDto commentDto, Educator educator, User user);
}
