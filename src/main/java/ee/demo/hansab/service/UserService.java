package ee.demo.hansab.service;

import ee.demo.hansab.dto.CarDto;
import ee.demo.hansab.dto.UserDto;
import java.util.List;

public interface UserService {

  List<UserDto> fetchUsers();

  UserDto fetchUser(Long userId);

  List<CarDto> fetchUserCars(Long userId);
}
