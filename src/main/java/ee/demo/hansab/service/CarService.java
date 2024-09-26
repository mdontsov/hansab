package ee.demo.hansab.service;

import ee.demo.hansab.dto.CarDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface CarService {

  List<CarDto> fetchCars();

  CarDto fetchCar(Long carId);
}
