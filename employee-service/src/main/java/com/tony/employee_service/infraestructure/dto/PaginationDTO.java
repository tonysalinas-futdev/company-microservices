package com.tony.employee_service.infraestructure.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
public class PaginationDTO {
  private final int page = 0;
  private final int items = 10;
}
