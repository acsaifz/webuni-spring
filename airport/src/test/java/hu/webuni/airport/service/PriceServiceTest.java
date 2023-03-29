package hu.webuni.airport.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PriceServiceTest {
    @InjectMocks
    private PriceService priceService;

    @Mock
    private DiscountService discountService;


    @Test
    void itShouldReturnDiscountedFinalPrice(){
        int price = new PriceService(p -> 5).getFinalPrice(100);
        //assertEquals(95, price);
        assertThat(price).isEqualTo(price);
    }

    @Test
    void itShouldReturnDiscountedFinalPrice2(){
        //GIVEN
        when(discountService.getDiscountPercent(100)).thenReturn(5);

        //WHEN
        int actual = priceService.getFinalPrice(100);

        //THEN
        assertThat(actual).isEqualTo(95);
    }
}
