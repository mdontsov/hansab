package ee.demo.hansab.dto;

import ee.demo.hansab.entity.Car;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PersonDto {

  Long id;

  String name;

  List<Car> cars;
}
