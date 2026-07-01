package com.tony.employee_service.infraestructure.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ErrorResponseDTO {
  private final String message;
  private final String url;
  private final LocalDateTime datetime;

  public ErrorResponseDTO(String message, String url) {
    this.message = message;
    this.url = url;
    this.datetime = LocalDateTime.now();
  }
}
