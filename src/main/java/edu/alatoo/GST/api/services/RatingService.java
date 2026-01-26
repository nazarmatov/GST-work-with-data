package edu.alatoo.GST.api.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.alatoo.GST.api.dto.RatingDto;
import edu.alatoo.GST.api.exceptions.ResourceNotFoundException;
import edu.alatoo.GST.api.mappers.RatingMapper;
import edu.alatoo.GST.data.models.Educator;
import edu.alatoo.GST.data.models.Rating;
import edu.alatoo.GST.data.models.User;
import edu.alatoo.GST.data.repositories.EducatorRepository;
import edu.alatoo.GST.data.repositories.RatingRepository;
import edu.alatoo.GST.data.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class RatingService {
    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final EducatorRepository educatorRepository;
    private final RatingMapper ratingMapper;

    public RatingDto getRatingByIdAndEducator(Long id, Long educatorId){
        log.info("get rating with id {}", id);
        Educator educator = educatorRepository.findById(educatorId).orElseThrow(() -> new ResourceNotFoundException("edu netu"));
        RatingDto ratingDto = ratingMapper.toDto(ratingRepository.findByIdAndEducator(id, educator).orElseThrow(() -> new ResourceNotFoundException("netu rating")));
        return ratingDto;
    }
    
    public RatingDto createRating(RatingDto ratingDto,Long educatorId, Long userId) {
        log.info("creating rating: {}", ratingDto);
        Educator educator = educatorRepository.findById(educatorId).orElseThrow(() -> new ResourceNotFoundException("can not find educator"));
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("can not find user"));
        if (ratingRepository.existsByEducatorAndUser(educator, user)) {
            throw new IllegalStateException("User already rated this educator");
        }
        Rating rating = ratingMapper.toEntity(ratingDto, educator, user);
        Rating savedRating = ratingRepository.save(rating);
        return ratingMapper.toDto(savedRating);
    }

    public RatingDto updateRating(Long id, RatingDto ratingDto, Long educatorID) {
        log.info("updating rating with id: {}", id);
        Educator educator = educatorRepository.findById(educatorID).orElseThrow(() -> new ResourceNotFoundException("can not found educator by id for update rating"));
        Rating rating = ratingRepository.findByIdAndEducator(id, educator).orElseThrow(() -> new ResourceNotFoundException("can not find rating"));
        rating.setValue(ratingDto.getValue());
        Rating savedRating = ratingRepository.save(rating);
        return ratingMapper.toDto(savedRating);
    }

    @Transactional(readOnly = true)
    public Page<RatingDto> getAllByEducatorId(Long educatorId, Pageable pageable){
        log.info("get ratings of educator with id {}", educatorId);
        Educator educator = educatorRepository.findById(educatorId).orElseThrow(() -> new ResourceNotFoundException("cao not find educator"));
        Page<RatingDto> ratings = ratingRepository.findAllByEducator(educator, pageable).map(ratingMapper::toDto);
        return ratings;
    }

    public void deleteRating(Long id, Long educatorId) {
        log.info("deleting rating with id: {}", id);
        Educator educator = educatorRepository.findById(educatorId).orElseThrow(() -> new ResourceNotFoundException("can not found educator for deleting Rating"));
        if (!ratingRepository.existsByIdAndEducator(id, educator)) {
            throw new ResourceNotFoundException("can not find rating");
        }
        ratingRepository.deleteByIdAndEducator(id, educator);
    }
}
