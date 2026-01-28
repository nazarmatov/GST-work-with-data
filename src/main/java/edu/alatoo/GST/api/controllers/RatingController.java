package edu.alatoo.GST.api.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import edu.alatoo.GST.api.dto.RatingDto;
import edu.alatoo.GST.api.services.RatingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Validated
@Slf4j
@RequestMapping("/api/v1/educators/{educatorId}/ratings")
@RequiredArgsConstructor
public class RatingController {
    private final RatingService ratingService;

    @GetMapping("/{id}")
    public ResponseEntity<RatingDto> getRatingtById(@PathVariable Long id, @PathVariable Long educatorId){
        log.info("get comment with id {}", id);
        RatingDto ratingDto = ratingService.getRatingByIdAndEducator(id, educatorId);
        return ResponseEntity.ok(ratingDto);
    }

    @GetMapping
    public ResponseEntity<Page<RatingDto>> getRatingsByEducator(@PathVariable long educatorId,@PageableDefault(size = 20) Pageable pageable){
        log.info("get all ratings of educator");
        Page<RatingDto> ratings = ratingService.getAllByEducatorId(educatorId, pageable);
        return ResponseEntity.ok(ratings);
    }

    @PostMapping
    public ResponseEntity<RatingDto> createRating(@Valid @RequestBody RatingDto ratingDto, @PathVariable Long educatorId, @PathVariable Long userId){
        log.info("create rating {} for educator with ID {}", ratingDto, educatorId);
        RatingDto createdRating = ratingService.createRating(ratingDto, educatorId, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRating);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RatingDto> updateRating(@PathVariable Long id, @Valid @RequestBody RatingDto ratingDto, @PathVariable Long educatorId){
        log.info("update rating");
        RatingDto updatedRating = ratingService.updateRating(id, ratingDto, educatorId);
        return ResponseEntity.ok(updatedRating);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating( @PathVariable Long id, @PathVariable Long educatorId){
        log.info("delete Rating");
        ratingService.deleteRating(id,educatorId);
        return ResponseEntity.noContent().build();
    }

}
