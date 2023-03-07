package hu.webuni.airport;

import hu.webuni.airport.configuration.AirportConfigProperties;
import hu.webuni.airport.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {AirportConfigProperties.class})
public class AirportApplication implements CommandLineRunner {

    @Autowired
    private PriceService priceService;


    @Override
    public void run(String... args) throws Exception {
        System.out.println(priceService.getFinalPrice(200));
        System.out.println(priceService.getFinalPrice(20_000));
    }

    public static void main(String[] args) {
        SpringApplication.run(AirportApplication.class, args);
    }
}
