package hu.webuni.airport.repository;

import hu.webuni.airport.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    boolean existsByIataAndIdIsNot(String iata, long id);
}
