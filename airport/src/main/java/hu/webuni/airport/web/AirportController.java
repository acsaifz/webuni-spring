package hu.webuni.airport.web;

import hu.webuni.airport.dto.AirportDto;
import hu.webuni.airport.mapper.AirportMapper;
import hu.webuni.airport.model.Airport;
import hu.webuni.airport.service.AirportService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
public class AirportController {
    private final AirportService airportService;
    private final AirportMapper airportMapper;

    public AirportController(AirportService airportService, AirportMapper airportMapper) {
        this.airportService = airportService;
        this.airportMapper = airportMapper;
    }

    @GetMapping
    public List<AirportDto> getAll(){
        return airportMapper.airportsToDtos(
                        airportService.findAll()
        );
    }

    @GetMapping("/{id}")
    public AirportDto getById(@PathVariable Long id){
        return airportMapper.airportToDto(
                airportService.findById(id)
        );
    }

    @PostMapping
    public AirportDto createAirport(@Valid @RequestBody AirportDto airportDto){
        Airport airport = airportMapper.dtoToAirport(airportDto);

        return airportMapper.airportToDto(
                airportService.save(airport)
        );
    }

    @PutMapping("/{id}")
    public AirportDto modifyAirport(@RequestBody AirportDto airportDto, @PathVariable Long id){
        airportDto.setId(id);
        Airport airport = airportMapper.dtoToAirport(airportDto);

        return airportMapper.airportToDto(
                airportService.save(airport)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirport(@PathVariable Long id){
        airportService.delete(id);

        return new ResponseEntity<>(
                HttpStatus.NO_CONTENT
        );
    }
}
