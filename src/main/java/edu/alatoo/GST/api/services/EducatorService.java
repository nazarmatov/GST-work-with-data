package edu.alatoo.GST.api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.alatoo.GST.api.dto.EducatorDto;
import edu.alatoo.GST.api.exceptions.BadRequestException;
import edu.alatoo.GST.api.exceptions.ResourceNotFoundException;
import edu.alatoo.GST.api.mappers.EducatorMapper;
import edu.alatoo.GST.data.models.Educator;
import edu.alatoo.GST.data.models.Subject;
import edu.alatoo.GST.data.repositories.EducatorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class EducatorService {
    private final EducatorRepository educatorRepository;
    private final EducatorMapper educatorMapper;


    public EducatorDto createEducator(EducatorDto educatorDto) {
        log.info("creating educator: {}", educatorDto);
        try {
            Educator educator = educatorMapper.toEntity(educatorDto);
            Educator savedEducator = educatorRepository.save(educator);
            return educatorMapper.toDto(savedEducator);
        } catch (Exception e) {
            throw new BadRequestException("Error creating educator: " + e.getMessage());
        }
    }

    public EducatorDto getEducatorById(Long id) {
        log.info("getting educator by id: {}", id);
        return educatorMapper.toDto(educatorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("educator with id: " + id + " not found")));
    }

    public EducatorDto updateEducator(Long id, EducatorDto educatorDto) {
        log.info("updating educator with id: {}", id);
        Educator existingEducator = educatorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("educator with id: " + id + " not found"));
        existingEducator.setFirstName(educatorDto.getFirstName());
        existingEducator.setLastName(educatorDto.getLastName());
        existingEducator.setAverageRating(educatorDto.getAverageRating());
        existingEducator.setSubjects(
            educatorDto.getSubjects()
                .stream()
                .map(title -> {
                    Subject subject = new Subject();
                    subject.setTitle(title);
                    return subject;
                })
                .collect(Collectors.toSet())
        );
                
        Educator saved = educatorRepository.save(existingEducator);
        return educatorMapper.toDto(saved);
    }

    public void deleteEducator(Long id) {
        log.info("deleting educator with id: {}", id);
        if (!educatorRepository.existsById(id)) {
            throw new ResourceNotFoundException("educator with id: " + id + " not found");
        }
        educatorRepository.deleteById(id);
    }

    public List<EducatorDto> getAllEducators(){
        log.info("getting all educators");
        return educatorRepository.findAll().stream()
                .map(educatorMapper::toDto)
                .collect(Collectors.toList());
    }
}
