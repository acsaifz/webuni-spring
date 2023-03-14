package hu.webuni.airport.web;

import hu.webuni.airport.dto.AirportDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AirportTLController {
    private final List<AirportDto> allAirports = new ArrayList<>();

    {
        allAirports.add(new AirportDto(1, "Ferenc Liszt Airport", "BUD"));
    }


    @GetMapping("/")
    public String home(){
        System.out.println("asdf");
        return "index";
    }

    @GetMapping("/airports")
    public String listAirports(Model model){
        model.addAttribute("airports", allAirports);
        model.addAttribute("newAirport", new AirportDto());
        return "airports";
    }

    @PostMapping("/airports")
    public String addAirport(AirportDto airportDto){
        allAirports.add(airportDto);
        return "redirect:/airports";
    }
}
