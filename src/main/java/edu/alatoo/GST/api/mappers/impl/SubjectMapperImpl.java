package edu.alatoo.GST.api.mappers.impl;

import org.springframework.stereotype.Component;

import edu.alatoo.GST.api.dto.SubjectDto;
import edu.alatoo.GST.api.mappers.SubjectMapper;
import edu.alatoo.GST.data.models.Subject;

@Component
public class SubjectMapperImpl implements SubjectMapper {
    @Override
    public SubjectDto toDto(Subject subject){
        if(subject == null) return null;

        SubjectDto subjectDto = SubjectDto.builder()
                .id(subject.getId())
                .title(subject.getTitle())
                .build();

        return subjectDto;
    }

    @Override
    public Subject toEntity(SubjectDto subjectDto){
        if(subjectDto == null) return null;

        Subject subject = Subject.builder()
                .id(subjectDto.getId())
                .title(subjectDto.getTitle())
                .build();

        return subject;
    }
}
