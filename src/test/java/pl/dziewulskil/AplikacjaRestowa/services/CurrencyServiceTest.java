package pl.dziewulskil.AplikacjaRestowa.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.dziewulskil.AplikacjaRestowa.client.NbpWebClient;
import pl.dziewulskil.AplikacjaRestowa.dtos.CurrencyEntityDto;
import pl.dziewulskil.AplikacjaRestowa.dtos.CurrencyRateDto;
import pl.dziewulskil.AplikacjaRestowa.dtos.CurrencyTableDto;
import pl.dziewulskil.AplikacjaRestowa.dtos.ValueDto;
import pl.dziewulskil.AplikacjaRestowa.entity.CurrencyEntity;
import pl.dziewulskil.AplikacjaRestowa.mappers.CurrencyMapper;
import pl.dziewulskil.AplikacjaRestowa.repositories.CurrencyRepository;
import pl.dziewulskil.AplikacjaRestowa.requests.CurrencyInfoRequest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceTest {

    @Mock
    private NbpWebClient nbpWebClient;

    @Mock
    private CurrencyMapper currencyMapper;

    @Mock
    private CurrencyRepository currencyRepository;

    @InjectMocks
    private CurrencyService currencyService;

    @Test
    void getCurrentCurrencyValueTest() {
        // given
        CurrencyEntity currencyEntity = mock(CurrencyEntity.class);
        ValueDto valueDto = mock(ValueDto.class);
        CurrencyInfoRequest currencyInfoRequest = CurrencyInfoRequest.builder()
                .code("USD")
                .build();
        CurrencyRateDto currencyRateDto = CurrencyRateDto.builder()
                .code("USD")
                .build();

        List<CurrencyRateDto> rates = Collections.singletonList(currencyRateDto);
        CurrencyTableDto currencyTableDto = new CurrencyTableDto(rates);
        List<CurrencyTableDto> currencyTableDtoList = Collections.singletonList(currencyTableDto);

        when(nbpWebClient.getCurrencyTable()).thenReturn(currencyTableDtoList);
        when(currencyMapper.mapToCurrencyEntity(currencyInfoRequest, currencyRateDto)).thenReturn(currencyEntity);
        when(currencyRepository.save(currencyEntity)).thenReturn(currencyEntity);
        when(currencyMapper.mapToValueDto(currencyRateDto)).thenReturn(valueDto);

        // when
        ValueDto result = currencyService.getCurrentCurrencyValue(currencyInfoRequest);

        // then
        assertEquals(valueDto, result);
        verify(nbpWebClient).getCurrencyTable();
        verify(currencyMapper).mapToCurrencyEntity(currencyInfoRequest, currencyRateDto);
        verify(currencyRepository).save(currencyEntity);
        verify(currencyMapper).mapToValueDto(currencyRateDto);
    }

    @Test
    void getAllRequestTest() {
        // given
        CurrencyEntityDto currencyEntityDto = mock(CurrencyEntityDto.class);
        List<CurrencyEntityDto> currencyEntityDtos = List.of(currencyEntityDto);
        CurrencyEntity currencyEntity = mock(CurrencyEntity.class);
        List<CurrencyEntity> currencyEntities = List.of(currencyEntity);

        when(currencyRepository.findAll()).thenReturn(currencyEntities);
        when(currencyMapper.mapToCurrencyEntityDtos(currencyEntities)).thenReturn(currencyEntityDtos);

        // when
        List<CurrencyEntityDto> result = currencyService.getAllRequest();

        // then
        assertEquals(currencyEntityDtos, result);
        verify(currencyRepository).findAll();
        verify(currencyMapper).mapToCurrencyEntityDtos(currencyEntities);

    }
}