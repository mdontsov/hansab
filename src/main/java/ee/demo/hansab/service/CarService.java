package ee.demo.hansab.service;

import ee.demo.hansab.dto.CarDto;
import java.util.List;

public interface CarService {

  List<CarDto> fetchCars();

  CarDto fetchCarBy(Long id);
}
