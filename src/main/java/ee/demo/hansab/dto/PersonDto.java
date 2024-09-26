package ee.demo.hansab.dto;

import ee.demo.hansab.entity.Car;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PersonDto {

  Long id;

  String name;

  Set<Car> cars;
}
