package ee.demo.hansab.service;

import ee.demo.hansab.dto.CarDto;
import ee.demo.hansab.dto.PersonDto;
import java.util.List;

public interface PersonService {

  List<PersonDto> fetchPersons(
      String filter,
      String sorting
  );

  PersonDto fetchPersonBy(Long id);

  List<CarDto> fetchCarsBy(Long personId);
}
