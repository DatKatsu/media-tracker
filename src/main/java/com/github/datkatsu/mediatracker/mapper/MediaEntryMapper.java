package com.github.datkatsu.mediatracker.mapper;

import com.github.datkatsu.mediatracker.dto.MediaEntryRequestDto;
import com.github.datkatsu.mediatracker.dto.MediaEntryResponseDto;
import com.github.datkatsu.mediatracker.dto.MediaEntryUpdateDto;
import com.github.datkatsu.mediatracker.model.MediaEntry;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MediaEntryMapper {
    MediaEntryResponseDto toResponseDto(MediaEntry entity);
    List<MediaEntryResponseDto> toResponseDtoList(List<MediaEntry> entities);

    MediaEntry toEntity(MediaEntryRequestDto dto);
    MediaEntry toEntity(MediaEntryUpdateDto dto);

}
