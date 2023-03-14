package hu.webuni.airport.web;

import hu.webuni.airport.dto.AirportDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/airports")
public class AirportController {
    private Long nextAirportId = 1L;
    private final Map<Long, AirportDto> airports = new HashMap<>();


    {
        airports.put(nextAirportId, new AirportDto(nextAirportId++, "abc", "XYZ"));
        airports.put(nextAirportId, new AirportDto(nextAirportId++, "def", "UVW"));
    }

    @GetMapping
    public List<AirportDto> getAll(){
        return new ArrayList<>(airports.values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirportDto> getById(@PathVariable Long id){
        AirportDto airportDto = airports.get(id);

        if (airportDto != null) {
            return new ResponseEntity<>(
                    airportDto,
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PostMapping
    public ResponseEntity<AirportDto> createAirport(@RequestBody AirportDto airportDto){
        airportDto.setId(nextAirportId);
        airports.put(nextAirportId++, airportDto);

        return new ResponseEntity<>(
                airportDto,
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<AirportDto> modifyAirport(@RequestBody AirportDto airportDto, @PathVariable Long id){
        if (!airports.containsKey(id)){
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND
            );
        }

        airportDto.setId(id);
        airports.put(id,airportDto);

        return new ResponseEntity<>(
                airportDto,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirport(@PathVariable Long id){
        airports.remove(id);
        return new ResponseEntity<>(
                HttpStatus.NO_CONTENT
        );
    }
}
