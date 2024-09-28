package ee.demo.hansab.controller;

import ee.demo.hansab.dto.CarDto;
import ee.demo.hansab.service.CarService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
@RequestMapping("/cars")
public class CarController {

  private final CarService service;

  @GetMapping
  public ResponseEntity<List<CarDto>> fetchPersons(
      @RequestParam(value = "find", required = false) String findBy,
      @RequestParam(value = "sort", required = false) String sortingOrder
  ) {
    List<CarDto> cars = service.fetchCars(findBy, sortingOrder);
    return cars.isEmpty() ?
        ResponseEntity.noContent()
            .build() :
        ResponseEntity.ok(cars);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CarDto> fetchCarBy(@PathVariable Long id) {
    var car = service.fetchCarBy(id);
    return car.getId() == null ?
        ResponseEntity.noContent()
            .build() :
        ResponseEntity.ok(car);
  }
}
