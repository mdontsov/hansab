package ee.demo.hansab.service.impl;

import ee.demo.hansab.dto.CarDto;
import ee.demo.hansab.dto.PersonDto;
import ee.demo.hansab.entity.Car;
import ee.demo.hansab.entity.Person;
import ee.demo.hansab.repository.PersonRepo;
import ee.demo.hansab.service.PersonService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class PersonServiceImpl implements PersonService {

  private final PersonRepo personRepo;

  @Override
  public List<PersonDto> fetchPersons(
      String findBy,
      String sort
  ) {
    if (hasFiltering(findBy) && noSorting(sort)) {
      log.info("Filtering results by {}", findBy);
      return personRepo.findByNameContainingIgnoreCase(findBy, null)
          .stream()
          .map(this::asPersonDto)
          .collect(Collectors.toList());
    }
    if (hasFiltering(findBy)) {
      Sort sortingOrder = sort.contains(":asc") ?
          Sort.by("name")
              .ascending() :
          Sort.by("name")
              .descending();
      log.info("Filtering results by {} using {} order", findBy, sortingOrder);
      return personRepo.findByNameContainingIgnoreCase(findBy, sortingOrder)
          .stream()
          .map(this::asPersonDto)
          .collect(Collectors.toList());
    }
    return personRepo.findAll()
        .stream()
        .map(this::asPersonDto)
        .collect(Collectors.toList());
  }

  @Override
  public PersonDto fetchPersonBy(Long id) {
    var person = personRepo.findById(id);

    log.info("Fetching the person by ID: {}", id);
    return person.isEmpty() ?
        asEmptyPerson() :
        asPersonDto(person.get());
  }

  @Override
  public List<CarDto> fetchCarsBy(Long personId) {
    var person = personRepo.findById(personId);
    log.info("Fetching the car(s) by person ID: {}", personId);

    List<CarDto> cars = person.map(it -> it.getCars()
            .stream()
            .map(this::asCarDto)
            .collect(Collectors.toList()))
        .orElseGet(ArrayList::new);

    log.info("Found car(s): {}", cars);
    return cars;
  }

  private boolean hasFiltering(String filter) {
    return filter != null;
  }

  private boolean noSorting(String sorting) {
    return sorting == null;
  }

  private PersonDto asPersonDto(Person person) {
    return PersonDto.builder()
        .id(person.getId())
        .name(person.getName())
        .cars(person.getCars())
        .build();
  }

  private PersonDto asEmptyPerson() {
    return PersonDto.builder()
        .build();
  }

  private CarDto asCarDto(Car car) {
    return CarDto.builder()
        .id(car.getId())
        .make(car.getMake())
        .model(car.getModel())
        .numberplate(car.getNumberplate())
        .build();
  }
}
