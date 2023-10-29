package ru.practicum.mainservice.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.mainservice.dto.location.CreateLocationDto;
import ru.practicum.mainservice.dto.location.LocationDto;
import ru.practicum.mainservice.model.Location;

@Component
public class LocationMapper {
    public Location fromDto(CreateLocationDto dto) {
        if (dto == null)
            return null;
        Location location = new Location();
        location.setLon(dto.getLon());
        location.setLat(dto.getLat());
        return location;
    }

    public LocationDto toModel(Location location) {
        if (location == null)
            return null;
        LocationDto dto = new LocationDto();
        dto.setId(location.getId());
        dto.setLon(location.getLon());
        dto.setLat(location.getLat());
        return dto;
    }
}
