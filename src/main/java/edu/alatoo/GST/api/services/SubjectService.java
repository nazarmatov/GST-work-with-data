package edu.alatoo.GST.api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.alatoo.GST.api.dto.SubjectDto;
import edu.alatoo.GST.api.exceptions.BadRequestException;
import edu.alatoo.GST.api.exceptions.ResourceNotFoundException;
import edu.alatoo.GST.data.models.Subject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class SubjectService {
    private final edu.alatoo.GST.data.repositories.SubjectRepository subjectRepository;
    private final edu.alatoo.GST.api.mappers.SubjectMapper subjectMapper;

    public SubjectDto createSubject(SubjectDto subjectDto) {
        log.info("Creating subject with title: {}", subjectDto.getTitle());
        if (subjectRepository.findByTitle(subjectDto.getTitle()).isPresent()) {
            throw new BadRequestException("Subject with title: " + subjectDto.getTitle() + " already exists");
        }
        try {
            Subject subject = subjectMapper.toEntity(subjectDto);
            Subject savedSubject = subjectRepository.save(subject);
            return subjectMapper.toDto(savedSubject);
        } catch (Exception e) {
            log.error("Error with creating subject");
            throw new BadRequestException("Error with creating subject");
        }
    }

    @Transactional(readOnly = true)
    public SubjectDto getSubjectById(Long id) {
        log.info("Getting subject with id: {}", id);
        return subjectMapper.toDto(subjectRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Subject with id: " + id + " not found")));
    }

    @Transactional(readOnly = true)
    public List<SubjectDto> getAllSubjects() {
        log.info("Getting all subjects");
        return subjectRepository.findAll().stream()
                .map(subjectMapper::toDto)
                .collect(Collectors.toList());
    }

    public void deleteSubject(Long id){
        log.info("deleting subject");
        if (!subjectRepository.existsById(id)) {
            throw new BadRequestException("can not find subject");
        }
        subjectRepository.deleteById(id);
    }
}
