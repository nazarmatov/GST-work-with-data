package edu.alatoo.GST.api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.alatoo.GST.api.dto.SubjectDto;
import edu.alatoo.GST.api.services.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Validated
@Slf4j
@RequestMapping("/api/v1/subjects")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDto> getSubjectById(@PathVariable Long id){
        log.info("get subject with id {}", id);
        SubjectDto subjectDto = subjectService.getSubjectById(id);
        return ResponseEntity.ok(subjectDto);
    }

    @GetMapping
    public ResponseEntity<List<SubjectDto>> getAllSubjects(){
        log.info("get all subjects");
        List<SubjectDto> subjects = subjectService.getAllSubjects();
        return ResponseEntity.ok(subjects);
    }

    @PostMapping
    public ResponseEntity<SubjectDto> createSubject(@Valid @RequestBody SubjectDto subjectDto){
        log.info("create subject {}",subjectDto);
        SubjectDto createdSubject = subjectService.createSubject(subjectDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSubject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id){
        log.info("delete subject with id {}", id);
        subjectService.deleteSubject(id);
        return ResponseEntity.noContent().build();
    }

}
