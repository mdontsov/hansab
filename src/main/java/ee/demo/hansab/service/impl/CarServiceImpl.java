package ee.demo.hansab.service.impl;

import ee.demo.hansab.dto.CarDto;
import ee.demo.hansab.entity.Car;
import ee.demo.hansab.repository.CarRepo;
import ee.demo.hansab.service.CarService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class CarServiceImpl implements CarService {

  private final CarRepo carRepo;

  @Override
  public List<CarDto> fetchCars(
      String findBy,
      String sort
  ) {
    if (hasFiltering(findBy) && noSorting(sort)) {
      log.info("Filtering results by {}", findBy);
      return carRepo.findByMakeContainingIgnoreCase(findBy, null)
          .stream()
          .map(this::asCarDto)
          .collect(Collectors.toList());
    }
    if (hasFiltering(findBy)) {
      Sort sortingOrder = sort.contains(":asc") ?
          Sort.by("make", "model", "numberplate")
              .ascending() :
          Sort.by("make", "model", "numberplate")
              .descending();
      log.info("Filtering results by {} using {} order", findBy, sortingOrder);
      return carRepo.findByMakeContainingIgnoreCase(findBy, sortingOrder)
          .stream()
          .map(this::asCarDto)
          .collect(Collectors.toList());
    }
    return carRepo.findAll()
        .stream()
        .map(this::asCarDto)
        .collect(Collectors.toList());
  }

  @Override
  public CarDto fetchCarBy(Long id) {
    var car = carRepo.findById(id);

    log.info("Fetching the car by ID: {}", id);
    return car.isEmpty() ?
        asEmptyCar() :
        asCarDto(car.get());
  }

  private boolean hasFiltering(String filter) {
    return filter != null;
  }

  private boolean noSorting(String sorting) {
    return sorting == null;
  }

  private CarDto asCarDto(Car car) {
    return CarDto.builder()
        .id(car.getId())
        .make(car.getMake())
        .model(car.getModel())
        .numberplate(car.getNumberplate())
        .build();
  }

  private CarDto asEmptyCar() {
    return CarDto.builder()
        .build();
  }
}
