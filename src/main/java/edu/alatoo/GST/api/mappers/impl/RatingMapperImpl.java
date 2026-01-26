package edu.alatoo.GST.api.mappers.impl;

import org.springframework.stereotype.Component;

import edu.alatoo.GST.api.dto.RatingDto;
import edu.alatoo.GST.api.mappers.RatingMapper;
import edu.alatoo.GST.data.models.Educator;
import edu.alatoo.GST.data.models.Rating;
import edu.alatoo.GST.data.models.User;

@Component
public class RatingMapperImpl implements RatingMapper {
    @Override
    public RatingDto toDto(Rating rating){
        if(rating == null) return null;
        
        RatingDto ratingDto = RatingDto.builder()
                .id(rating.getId())
                .createdAt(rating.getCreatedAt())
                .value(rating.getValue())
                .educatorId(rating.getEducator().getId())
                .userId(rating.getUser().getId())
                .build();

        return ratingDto;
    }

    @Override
    public Rating toEntity(RatingDto ratingDto, Educator educator, User user){
        if(ratingDto == null) return null;

        Rating rating = Rating.builder()
                .id(ratingDto.getId())
                .createdAt(ratingDto.getCreatedAt())
                .value(ratingDto.getValue())
                .educator(educator)
                .user(user)
                .build();

        return rating;
    }
}
