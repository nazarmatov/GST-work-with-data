package edu.alatoo.GST.api.mappers.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import edu.alatoo.GST.api.dto.EducatorDto;
import edu.alatoo.GST.api.mappers.EducatorMapper;
import edu.alatoo.GST.data.models.Educator;
import edu.alatoo.GST.data.models.Subject;

@Component
public class EducatorMapperImpl implements EducatorMapper {
    @Override
    public EducatorDto toDto(Educator educator){
        if(educator == null) return null;

        Set<String> subjects = null;
        if (educator.getSubjects() != null) {
            subjects = educator.getSubjects()
                    .stream()
                    .map(Subject::getTitle)
                    .collect(Collectors.toSet());
        }

        EducatorDto educatorDto = EducatorDto.builder()
                .id(educator.getId())
                .firstName(educator.getFirstName())
                .lastName(educator.getLastName())
                .averageRating(educator.getAverageRating())
                .subjects(subjects)
                .build();

        return educatorDto;
    }

    @Override
    public Educator toEntity(EducatorDto educatorDto){
        if (educatorDto == null) return null;

        Set<Subject> subjects = null;
        if (educatorDto.getSubjects() != null) {
            subjects = educatorDto.getSubjects().stream()
                    .map(tltle -> {
                        Subject subject = new Subject();
                        subject.setTitle(tltle);
                        return subject;
                    })
                    .collect(Collectors.toSet());
        }

        Educator educator = Educator.builder()
                .id(educatorDto.getId())
                .firstName(educatorDto.getFirstName())
                .lastName(educatorDto.getLastName())
                .averageRating(educatorDto.getAverageRating())
                .subjects(subjects)
                .build();

        return educator;
    }
}