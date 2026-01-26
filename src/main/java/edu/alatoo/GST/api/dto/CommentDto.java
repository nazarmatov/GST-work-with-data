package edu.alatoo.GST.api.dto;

import java.time.Instant;

import jakarta.persistence.Column;
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
public class CommentDto {
    Long id;
    @NotNull
    @Column(length = 1000)
    String text;
    @NotNull
    Long educatorId;
    @NotNull
    Long userId;
    @NotNull
    Instant createdAt;
}