package edu.alatoo.GST.api.mappers;

import edu.alatoo.GST.api.dto.EducatorDto;
import edu.alatoo.GST.data.models.Educator;

public interface EducatorMapper {
    EducatorDto toDto(Educator educator);
    Educator toEntity(EducatorDto educatorDto);
}
