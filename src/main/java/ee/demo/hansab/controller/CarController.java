package ee.demo.hansab.controller;

import ee.demo.hansab.dto.CarDto;
import ee.demo.hansab.service.CarService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/")
public class CarController {

  private final CarService service;

  @GetMapping("cars")
  public ResponseEntity<List<CarDto>> fetchPersons() {
    var cars = service.fetchCars();
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(cars);
  }

  @GetMapping("cars/{id}")
  public ResponseEntity<CarDto> fetchCarBy(@PathVariable Long id) {
    var car = service.fetchCarBy(id);
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(car);
  }
}
