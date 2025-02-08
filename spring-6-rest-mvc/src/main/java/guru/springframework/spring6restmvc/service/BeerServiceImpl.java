package guru.springframework.spring6restmvc.service;

import guru.springframework.spring6restmvc.model.Beer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {
    @Override
    public Beer getBeerById(UUID id) {

        log.debug("Get Beer Id - in service. Id: " + id.toString());

        return Beer.builder()
                .id(id)
                .version(1)
                .beerName("Galaxy Cat")
                .upc("123456")
                .price(new BigDecimal("100.00"))
                .quantityOnHand(122)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
    }
}
