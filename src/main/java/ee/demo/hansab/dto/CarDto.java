package ee.demo.hansab.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CarDto {

  Long id;

  String make;

  String model;

  String numberplate;
}
