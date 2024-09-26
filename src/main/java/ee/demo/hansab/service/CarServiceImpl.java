package ee.demo.hansab.service;

import ee.demo.hansab.dto.CarDto;
import ee.demo.hansab.entity.Car;
import ee.demo.hansab.repository.CarRepo;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CarServiceImpl implements CarService {

  private final CarRepo carRepo;

  @Override
  public List<CarDto> fetchCars() {
    return carRepo
        .findAll()
        .stream()
        .map(this::asCarDto)
        .collect(Collectors.toList());
  }

  @Override
  public CarDto fetchCarBy(Long id) {
    var car = carRepo
        .findById(id)
        .orElseThrow();
    return CarDto
        .builder()
        .id(car.getId())
        .make(car.getMake())
        .model(car.getModel())
        .numberplate(car.getNumberplate())
        .build();
  }

  private CarDto asCarDto(Car car) {
    return CarDto
        .builder()
        .id(car.getId())
        .make(car.getMake())
        .model(car.getModel())
        .numberplate(car.getNumberplate())
        .build();
  }
}
