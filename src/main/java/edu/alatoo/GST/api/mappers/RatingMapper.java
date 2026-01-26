package edu.alatoo.GST.api.mappers;

import edu.alatoo.GST.api.dto.RatingDto;
import edu.alatoo.GST.data.models.Educator;
import edu.alatoo.GST.data.models.Rating;
import edu.alatoo.GST.data.models.User;

public interface RatingMapper {
    RatingDto toDto(Rating rating);
    Rating toEntity(RatingDto ratingDto, Educator educator, User user);
}
