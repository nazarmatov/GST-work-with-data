package edu.alatoo.GST.api.controllers;

import java.util.List;

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

import edu.alatoo.GST.api.dto.EducatorDto;
import edu.alatoo.GST.api.services.EducatorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Validated
@Slf4j
@RequestMapping("/api/v1/educators")
@RequiredArgsConstructor
public class EducatorController {
    private final EducatorService educatorService;

    @GetMapping("/{id}")
    public ResponseEntity<EducatorDto> getEducatorById(@PathVariable Long id){
        log.info("get Educator with id {}", id);
        EducatorDto educatorDto = educatorService.getEducatorById(id);
        return ResponseEntity.ok(educatorDto);
    }

    @GetMapping
    public ResponseEntity<List<EducatorDto>> getAllEducators(){
        log.info("get all educators");
        List<EducatorDto> educators = educatorService.getAllEducators();
        return ResponseEntity.ok(educators);
    }

    @PostMapping
    public ResponseEntity<EducatorDto> createEducator(@Valid @RequestBody EducatorDto educatorDto){
        log.info("create educator {}", educatorDto);
        EducatorDto craetedEducator = educatorService.createEducator(educatorDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(craetedEducator);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EducatorDto> updateEducator(@PathVariable Long id, @Valid @RequestBody EducatorDto educatorDto){
        log.info("update educatotr with id {}", id);
        EducatorDto updatedEducato = educatorService.updateEducator(id, educatorDto);
        return ResponseEntity.ok(updatedEducato);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEducator(@PathVariable Long id){
        log.info("delete educator with id {}", id);
        educatorService.deleteEducator(id);
        return ResponseEntity.noContent().build();
    }

}
