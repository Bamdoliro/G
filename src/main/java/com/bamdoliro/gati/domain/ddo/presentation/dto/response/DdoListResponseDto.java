package com.bamdoliro.gati.domain.ddo.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class DdoListResponseDto {
    private List<DdoResponseDto> ddoList;
}
