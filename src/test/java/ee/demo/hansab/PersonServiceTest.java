package ee.demo.hansab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ee.demo.hansab.dto.CarDto;
import ee.demo.hansab.dto.PersonDto;
import ee.demo.hansab.entity.Car;
import ee.demo.hansab.entity.Person;
import ee.demo.hansab.repository.PersonRepo;
import ee.demo.hansab.service.impl.PersonServiceImpl;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

  @Mock
  private PersonRepo personRepo;

  @InjectMocks
  private PersonServiceImpl personService;

  private final Person person1 = new Person();

  private final Person person2 = new Person();

  private final Car car1 = new Car();

  private final Car car2 = new Car();

  private final Car car3 = new Car();

  @BeforeEach
  void setUp() {
    car1.setId(1L);
    car1.setMake("Lada");
    car1.setModel("2101");
    car1.setNumberplate("123ASD");

    car2.setId(2L);
    car2.setMake("Kia");
    car2.setModel("Sorento");
    car2.setNumberplate("534TTT");

    car3.setId(3L);
    car3.setMake("Volvo");
    car3.setModel("740");
    car3.setNumberplate("577APX");

    person1.setId(1L);
    person1.setName("Teet Järveküla");
    person1.setCars(List.of(car1, car2));

    person2.setId(2L);
    person2.setName("Teet Kruus");
    person2.setCars(List.of(car3));
  }

  @Test
  void shouldFetchPersonWithFilterAndSortAscending() {
    // Given
    String findBy = "teet";
    String sort = "name:asc";
    List<Person> entityPersons = List.of(person1, person2);
    when(personRepo.findByNameContainingIgnoreCase(eq(findBy), any())).thenReturn(entityPersons);

    // When
    List<PersonDto> dtoPersons = personService.fetchPersons(findBy, sort);

    // Then
    assertNotNull(dtoPersons);
    assertEquals(entityPersons.size(), dtoPersons.size());
    assertEquals("Teet Järveküla",
        dtoPersons.get(0)
            .getName());
    verify(personRepo, times(1)).findByNameContainingIgnoreCase(eq(findBy), any());
  }

  @Test
  void shouldFetchPersonWithFilterAndSortDescending() {
    // Given
    String findBy = "teet";
    String sort = "name:asc";
    List<Person> entityPersons = List.of(person2, person1);
    when(personRepo.findByNameContainingIgnoreCase(eq(findBy), any())).thenReturn(entityPersons);

    // When
    List<PersonDto> dtoPersons = personService.fetchPersons(findBy, sort);

    // Then
    assertNotNull(dtoPersons);
    assertEquals(entityPersons.size(), dtoPersons.size());
    assertEquals("Teet Kruus",
        dtoPersons.get(0)
            .getName());
    verify(personRepo, times(1)).findByNameContainingIgnoreCase(eq(findBy), any());
  }

  @Test
  void shouldFetchPersonsWithoutFilteringOrSorting() {
    // Given
    List<Person> entityPersons = List.of(person1, person2);
    when(personRepo.findAll()).thenReturn(entityPersons);

    // When
    List<PersonDto> dtoPersons = personService.fetchPersons(null, null);

    // Then
    assertNotNull(dtoPersons);
    assertEquals(entityPersons.size(), dtoPersons.size());
    verify(personRepo, times(1)).findAll();
  }

  @Test
  void shouldFetchPersonById() {
    // Given
    when(personRepo.findById(1L)).thenReturn(Optional.of(person1));

    // When
    PersonDto personDto = personService.fetchPersonBy(person1.getId());

    // Then
    assertNotNull(personDto);
    assertEquals("Teet Järveküla", personDto.getName());
    verify(personRepo, times(1)).findById(person1.getId());
  }

  @Test
  void shouldFetchCarsByPersonId() {
    // Given
    when(personRepo.findById(1L)).thenReturn(Optional.of(person1));

    // When
    PersonDto personDto = personService.fetchPersonBy(person1.getId());
    List<CarDto> cars = personService.fetchCarsBy(personDto.getId());

    // Then
    assertEquals(2, cars.size());
    assertEquals("Lada",
        cars.get(0)
            .getMake());
    assertEquals("2101",
        cars.get(0)
            .getModel());
    assertEquals("123ASD",
        cars.get(0)
            .getNumberplate());
    assertEquals("Kia",
        cars.get(1)
            .getMake());
    assertEquals("Sorento",
        cars.get(1)
            .getModel());
    assertEquals("534TTT",
        cars.get(1)
            .getNumberplate());
    verify(personRepo, times(2)).findById(person1.getId());
  }

  @Test
  void shouldFetchNothing_ifPersonIdNotFound() {
    // Given
    when(personRepo.findById(10L)).thenReturn(Optional.empty());

    // When
    PersonDto personDto = personService.fetchPersonBy(10L);

    // Then
    assertNotNull(personDto); // Should return an empty PersonDto
    assertNull(personDto.getId());
    assertNull(personDto.getName());
    assertNull(personDto.getCars());
    verify(personRepo, times(1)).findById(10L);
  }
}
