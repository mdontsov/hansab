package ee.demo.hansab.repository;

import ee.demo.hansab.entity.Person;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepo extends JpaRepository<Person, Long> {

  List<Person> findByNameContainingIgnoreCase(
      String findBy,
      @Nullable Sort sort
  );
}
