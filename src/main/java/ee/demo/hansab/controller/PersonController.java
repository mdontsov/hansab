package ee.demo.hansab.controller;

import ee.demo.hansab.dto.PersonDto;
import ee.demo.hansab.service.PersonService;
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
public class PersonController {

  private final PersonService service;

  @GetMapping("users")
  public ResponseEntity<List<PersonDto>> fetchPersons() {
    var users = service.fetchPersons();
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(users);
  }

  @GetMapping("users/{id}")
  public ResponseEntity<PersonDto> fetchUserBy(@PathVariable Long id) {
    var user = service.fetchPersonBy(id);
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(user);
  }
}
