package hu.webuni.airport.service;

import hu.webuni.airport.exception.NonUniqueIataException;
import hu.webuni.airport.model.Airport;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class AirportService {
    private final AtomicLong airportIdGenerator = new AtomicLong();
    private final Map<Long, Airport> airports = new HashMap<>();

    public Airport save(Airport airport) {
        if (airport.getId() < 1) {
            airport.setId(airportIdGenerator.incrementAndGet());
        } else if (!airports.containsKey(airport.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        checkUniqueIata(airport);

        airports.put(airport.getId(), airport);

        return airport;
    }

    private void checkUniqueIata(Airport airport) {
        boolean isNonUnique = airports.values()
                .stream()
                .anyMatch(a -> a.getIata().equals(airport.getIata()) && a.getId() != airport.getId());

        if (isNonUnique){
            throw new NonUniqueIataException(airport.getIata());
        }
    }

    public List<Airport> findAll(){
        return new ArrayList<>(airports.values());
    }

    public Airport findById(long id){
        Airport airport = airports.get(id);

        if (airport == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return airport;
    }

    public void delete(long id){
        if(airports.remove(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    {
        airports.put(
                airportIdGenerator.incrementAndGet(),
                new Airport(airportIdGenerator.get(), "abc", "XYZ")
        );
        airports.put(
                airportIdGenerator.incrementAndGet(),
                new Airport(airportIdGenerator.get(), "def", "UVW")
        );
    }

}
