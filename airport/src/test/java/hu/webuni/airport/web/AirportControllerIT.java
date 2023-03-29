package hu.webuni.airport.web;

import hu.webuni.airport.dto.AirportDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class AirportControllerIT {
    private final static String BASE_URL = "/api/airports";

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testThatCreatedAirportIsListed() {
        //GIVEN
        List<AirportDto> airportsBefore = getAllAirports();
        AirportDto airportDto = new AirportDto("hsgjsfgj", "UZT");

        //WHEN
        createAirport(airportDto);
        List<AirportDto> airportsAfter = getAllAirports();

        //THEN
        assertThat(airportsAfter.subList(0, airportsAfter.size() - 1))
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyElementsOf(airportsBefore);

        assertThat(airportsAfter.get(airportsAfter.size() - 1))
                .isEqualTo(airportDto);
    }

    private void createAirport(AirportDto airportDto) {
        webTestClient.post()
                .uri(BASE_URL)
                .bodyValue(airportDto)
                .exchange()
                .expectStatus()
                .isOk();
    }

    private List<AirportDto> getAllAirports() {
        return webTestClient.get()
                .uri(BASE_URL)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(AirportDto.class)
                .returnResult()
                .getResponseBody();
    }
}
