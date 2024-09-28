package ee.demo.hansab.repository;

import ee.demo.hansab.entity.Car;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepo extends JpaRepository<Car, Long> {

  List<Car> findByMakeContainingIgnoreCase(
      String findBy,
      @Nullable Sort sort
  );
}
