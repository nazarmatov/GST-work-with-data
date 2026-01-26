package edu.alatoo.GST.api.mappers;

import edu.alatoo.GST.api.dto.SubjectDto;
import edu.alatoo.GST.data.models.Subject;

public interface SubjectMapper {
    SubjectDto toDto(Subject subject);
    Subject toEntity(SubjectDto subjectDto);
}
