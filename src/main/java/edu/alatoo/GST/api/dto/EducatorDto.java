package edu.alatoo.GST.api.dto;

import java.util.Set;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EducatorDto {
    Long id;
    @NotNull
    String firstName;
    @NotNull
    String lastName;
    Set<String> subjects;
    Double averageRating;
}
