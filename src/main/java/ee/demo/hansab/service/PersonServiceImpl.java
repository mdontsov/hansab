package ee.demo.hansab.service;

import ee.demo.hansab.dto.CarDto;
import ee.demo.hansab.dto.PersonDto;
import ee.demo.hansab.entity.Car;
import ee.demo.hansab.entity.Person;
import ee.demo.hansab.repository.PersonRepo;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PersonServiceImpl implements PersonService {

  private final PersonRepo personRepo;

  @Override
  public List<PersonDto> fetchPersons() {
    return personRepo
        .findAll()
        .stream()
        .map(this::asUserDto)
        .collect(Collectors.toList());
  }

  @Override
  public PersonDto fetchPersonBy(Long id) {
    var user = personRepo
        .findById(id)
        .orElseThrow();
    return PersonDto
        .builder()
        .id(user.getId())
        .name(user.getName())
        .build();
  }

  @Override
  public List<CarDto> fetchCarsBy(Long userId) {
    var user = personRepo
        .findById(userId)
        .orElseThrow();

    return user
        .getCars()
        .stream()
        .map(this::asCarDto)
        .collect(Collectors.toList());
  }

  private PersonDto asUserDto(Person person) {
    return PersonDto
        .builder()
        .id(person.getId())
        .name(person.getName())
        .cars(person.getCars())
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
