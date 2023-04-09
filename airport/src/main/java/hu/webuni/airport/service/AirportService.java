package hu.webuni.airport.service;

import hu.webuni.airport.exception.NonUniqueIataException;
import hu.webuni.airport.model.Airport;
import hu.webuni.airport.repository.AirportRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AirportService {
    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public Airport save(Airport airport) {
        if (airport.getId() > 0 && !airportRepository.existsById(airport.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        checkUniqueIata(airport);
        return airportRepository.save(airport);
    }

    public List<Airport> findAll(){
        return airportRepository.findAll();
    }

    public Airport findById(long id){
        return airportRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void delete(long id){
        if (airportRepository.existsById(id)) {
            airportRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    private void checkUniqueIata(Airport airport) {
        if (airportRepository.existsByIataAndIdIsNot(airport.getIata(), airport.getId())){
            throw new NonUniqueIataException(airport.getIata());
        }
    }

    /*{
        airports.put(
                airportIdGenerator.incrementAndGet(),
                new Airport(airportIdGenerator.get(), "abc", "XYZ")
        );
        airports.put(
                airportIdGenerator.incrementAndGet(),
                new Airport(airportIdGenerator.get(), "def", "UVW")
        );
    }*/

}
