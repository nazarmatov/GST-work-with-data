package edu.alatoo.GST.api.dto;

import java.time.Instant;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class RatingDto {
    Long id;
    @Min(1)
    @Max(5)
    @NotNull
    Integer value;
    @NotNull
    Instant createdAt;
    @NotNull
    Long educatorId;
    @NotNull
    Long userId;
}
