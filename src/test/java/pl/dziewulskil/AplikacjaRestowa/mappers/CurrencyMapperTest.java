package pl.dziewulskil.AplikacjaRestowa.mappers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.dziewulskil.AplikacjaRestowa.dtos.CurrencyEntityDto;
import pl.dziewulskil.AplikacjaRestowa.dtos.CurrencyRateDto;
import pl.dziewulskil.AplikacjaRestowa.dtos.ValueDto;
import pl.dziewulskil.AplikacjaRestowa.entity.CurrencyEntity;
import pl.dziewulskil.AplikacjaRestowa.requests.CurrencyInfoRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CurrencyMapperTest {

    @InjectMocks
    private CurrencyMapper currencyMapper;

    @Test
    void mapToValueDtoTest() {
        // given
        CurrencyRateDto currencyRateDto = new CurrencyRateDto(3.4562);

        // when
        ValueDto result = currencyMapper.mapToValueDto(currencyRateDto);

        // then
        assertEquals(result.getValue(), currencyRateDto.mid());
    }

    @Test
    void mapToCurrencyEntityTest() {
        // given
        CurrencyInfoRequest currencyInfoRequest = CurrencyInfoRequest.builder()
                .firstname("firstname")
                .lastname("lastname")
                .code("USD")
                .build();

        CurrencyRateDto currencyRateDto = new CurrencyRateDto(3.4562);

        // when
        CurrencyEntity result = currencyMapper.mapToCurrencyEntity(currencyInfoRequest,currencyRateDto);

        // then
        assertEquals(result.getFirstname(), currencyInfoRequest.getFirstname());
        assertEquals(result.getLastname(), currencyInfoRequest.getLastname());
        assertEquals(result.getCode(), currencyInfoRequest.getCode());
        assertEquals(result.getValue(), currencyRateDto.mid());
    }

    @Test
    void mapToCurrencyEntityDtosTest() {
        // given
        CurrencyEntity currencyEntity1 = CurrencyEntity.builder()
                .firstname("firstname1")
                .lastname("lastname1")
                .code("USD")
                .value(3.4562)
                .currency("dolar")
                .build();

        CurrencyEntity currencyEntity2 = CurrencyEntity.builder()
                .firstname("firstname2")
                .lastname("lastname2")
                .code("EUR")
                .value(4.3214)
                .currency("euro")
                .build();

        List<CurrencyEntity> currencyEntities = new ArrayList<>(Arrays.asList(currencyEntity1,currencyEntity2));

        // when
        List<CurrencyEntityDto> result = currencyMapper.mapToCurrencyEntityDtos(currencyEntities);

        // then
        assertEquals(2, result.size());
        assertEquals(result.get(0).getFirstname(), currencyEntity1.getFirstname());
        assertEquals(result.get(0).getLastname(), currencyEntity1.getLastname());
        assertEquals(result.get(0).getCode(), currencyEntity1.getCode());
        assertEquals(result.get(0).getValue(), currencyEntity1.getValue());
        assertEquals(result.get(0).getCurrency(), currencyEntity1.getCurrency());

        assertEquals(result.get(1).getFirstname(), currencyEntity2.getFirstname());
        assertEquals(result.get(1).getLastname(), currencyEntity2.getLastname());
        assertEquals(result.get(1).getCode(), currencyEntity2.getCode());
        assertEquals(result.get(1).getValue(), currencyEntity2.getValue());
        assertEquals(result.get(1).getCurrency(), currencyEntity2.getCurrency());
    }

}