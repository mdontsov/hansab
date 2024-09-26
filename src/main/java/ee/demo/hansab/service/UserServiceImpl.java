package ee.demo.hansab.service;

import ee.demo.hansab.dto.CarDto;
import ee.demo.hansab.dto.UserDto;
import ee.demo.hansab.entity.Car;
import ee.demo.hansab.entity.User;
import ee.demo.hansab.repository.UserRepo;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepo userRepo;

  @Override
  public List<UserDto> fetchUsers() {
    return userRepo
        .findAll()
        .stream()
        .map(this::asUserDto)
        .collect(Collectors.toList());
  }

  @Override
  public UserDto fetchUser(Long userId) {
    var user = userRepo
        .findById(userId)
        .orElseThrow();
    return UserDto
        .builder()
        .id(user.getId())
        .name(user.getName())
        .build();
  }

  @Override
  public List<CarDto> fetchUserCars(Long userId) {
    var user = userRepo
        .findById(userId)
        .orElseThrow();

    var cars = user.getCars();
    return cars
        .stream()
        .map(this::asCarDto)
        .collect(Collectors.toList());
  }

  private UserDto asUserDto(User user) {
    return UserDto
        .builder()
        .id(user.getId())
        .name(user.getName())
        .cars(user.getCars())
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
