package ee.demo.hansab.service;

import ee.demo.hansab.dto.CarDto;
import ee.demo.hansab.dto.PersonDto;
import java.util.List;

public interface PersonService {

  List<PersonDto> fetchPersons();

  PersonDto fetchPersonBy(Long id);

  List<CarDto> fetchCarsBy(Long userId);
}
