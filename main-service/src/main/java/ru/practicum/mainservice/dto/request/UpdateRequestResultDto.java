package ru.practicum.mainservice.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Builder
@Data
public class UpdateRequestResultDto {
    @Singular
    private List<RequestDto> confirmedRequests;
    @Singular
    private List<RequestDto> rejectedRequests;
}
