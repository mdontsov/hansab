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
import ee.demo.hansab.entity.Car;
import ee.demo.hansab.repository.CarRepo;
import ee.demo.hansab.service.impl.CarServiceImpl;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

  @Mock
  private CarRepo carRepo;

  @InjectMocks
  private CarServiceImpl carService;

  private final Car car1 = new Car();

  private final Car car2 = new Car();

  @BeforeEach
  void setUp() {
    car1.setId(1L);
    car1.setMake("Lada");
    car1.setModel("2101");
    car1.setNumberplate("123ASD");

    car2.setId(2L);
    car2.setMake("Lada");
    car2.setModel("2101");
    car2.setNumberplate("445KKK");
  }

  @Test
  public void shouldFetchCarsWithFilterAndSortAscending() {
    // Given
    String findBy = "lada";
    String sort = "numberplate:asc";
    List<Car> entityCars = List.of(car1, car2);

    when(carRepo.findByMakeContainingIgnoreCase(eq(findBy), any())).thenReturn(entityCars);

    // When
    List<CarDto> dtoCars = carService.fetchCars(findBy, sort);

    // Then
    assertNotNull(dtoCars);
    assertEquals(entityCars.size(), dtoCars.size());
    assertEquals("123ASD",
        dtoCars.get(0)
            .getNumberplate());
    verify(carRepo, times(1)).findByMakeContainingIgnoreCase(eq(findBy), any());
  }

  @Test
  public void shouldFetchCarsWithFilterAndSortDescending() {
    // Given
    String findBy = "lada";
    String sort = "numberplate:desc";
    List<Car> entityCars = List.of(car2, car1);

    when(carRepo.findByMakeContainingIgnoreCase(eq(findBy), any())).thenReturn(entityCars);

    // When
    List<CarDto> dtoCars = carService.fetchCars(findBy, sort);

    // Then
    assertNotNull(dtoCars);
    assertEquals(entityCars.size(), dtoCars.size());
    assertEquals("445KKK",
        dtoCars.get(0)
            .getNumberplate());
    verify(carRepo, times(1)).findByMakeContainingIgnoreCase(eq(findBy), any());
  }

  @Test
  public void shouldFetchCarsWithoutFilteringOrSorting() {
    // Given
    List<Car> entityCars = List.of(car1, car2);
    when(carRepo.findAll()).thenReturn(entityCars);

    // When
    List<CarDto> dtoCars = carService.fetchCars(null, null);

    // Then
    assertNotNull(dtoCars);
    assertEquals(entityCars.size(), dtoCars.size());
    verify(carRepo, times(1)).findAll();
  }

  @Test
  public void shouldFetchCarById() {
    // Given
    when(carRepo.findById(1L)).thenReturn(Optional.of(car1));

    // When
    CarDto carDto = carService.fetchCarBy(car1.getId());

    // Then
    assertNotNull(carDto);
    assertEquals("Lada", carDto.getMake());
    assertEquals("2101", carDto.getModel());
    assertEquals("123ASD", carDto.getNumberplate());
    verify(carRepo, times(1)).findById(car1.getId());
  }

  @Test
  public void shouldFetchNothing_ifCarIdNotFound() {
    // Given
    when(carRepo.findById(4L)).thenReturn(Optional.empty());

    // When
    CarDto carDto = carService.fetchCarBy(4L);

    // Then
    assertNotNull(carDto); // Should return an empty CarDto
    assertNull(carDto.getMake());
    assertNull(carDto.getModel());
    assertNull(carDto.getNumberplate());
    verify(carRepo, times(1)).findById(4L);
  }
}
