package hu.webuni.airport.service;

import hu.webuni.airport.configuration.AirportConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialDiscountService implements DiscountService {

    @Autowired
    AirportConfigProperties config;

    @Override
    public int getDiscountPercent(int totalPrice) {
        return totalPrice > config.getDiscount().getSpecial().getLimit()
                ? config.getDiscount().getSpecial().getPercent()
                : config.getDiscount().getDef().getPercent();
    }
}
