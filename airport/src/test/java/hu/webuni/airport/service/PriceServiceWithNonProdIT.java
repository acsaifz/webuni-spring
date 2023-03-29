package hu.webuni.airport.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class PriceServiceWithNonProdIT {

    @Autowired
    private PriceService priceService;

    @Test
    void itShouldReturnTenPercentDiscountedFinalPrice(){
        //GIVEN

        //WHEN
        int actual = priceService.getFinalPrice(100);

        //THEN
        assertThat(actual).isEqualTo(90);
    }

    @Test
    void itShouldReturnHigherDiscountedFinalPriceWhenPriceIsOverLimit(){
        //GIVEN

        //WHEN
        int actual = priceService.getFinalPrice(20_000);

        //THEN
        assertThat(actual).isEqualTo(18_000);
    }

}
