package ee.demo.hansab.controller;

import ee.demo.hansab.dto.CarDto;
import ee.demo.hansab.dto.PersonDto;
import ee.demo.hansab.service.PersonService;
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
@RequestMapping("/persons")
public class PersonController {

  private final PersonService service;

  @GetMapping
  public ResponseEntity<List<PersonDto>> fetchPersons(
      @RequestParam(value = "find", required = false) String findBy,
      @RequestParam(value = "sort", required = false) String sortingOrder
  ) {
    var persons = service.fetchPersons(findBy, sortingOrder);
    return persons.isEmpty() ?
        ResponseEntity.noContent()
            .build() :
        ResponseEntity.ok(persons);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PersonDto> fetchPersonBy(@PathVariable Long id) {
    var person = service.fetchPersonBy(id);
    return person.getId() == null ?
        ResponseEntity.noContent()
            .build() :
        ResponseEntity.ok(person);
  }

  @GetMapping("/{id}/cars")
  public ResponseEntity<List<CarDto>> fetchCarsBy(@PathVariable Long personId) {
    var cars = service.fetchCarsBy(personId);
    return cars.isEmpty() ?
        ResponseEntity.noContent()
            .build() :
        ResponseEntity.ok(cars);
  }
}
