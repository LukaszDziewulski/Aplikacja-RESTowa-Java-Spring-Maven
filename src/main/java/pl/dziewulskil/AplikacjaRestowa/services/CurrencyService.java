package pl.dziewulskil.AplikacjaRestowa.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.dziewulskil.AplikacjaRestowa.client.NbpWebClient;
import pl.dziewulskil.AplikacjaRestowa.dtos.CurrencyEntityDto;
import pl.dziewulskil.AplikacjaRestowa.dtos.CurrencyRateDto;
import pl.dziewulskil.AplikacjaRestowa.dtos.CurrencyTableDto;
import pl.dziewulskil.AplikacjaRestowa.dtos.ValueDto;
import pl.dziewulskil.AplikacjaRestowa.entity.CurrencyEntity;
import pl.dziewulskil.AplikacjaRestowa.exception.RestApplicationException;
import pl.dziewulskil.AplikacjaRestowa.mappers.CurrencyMapper;
import pl.dziewulskil.AplikacjaRestowa.repositories.CurrencyRepository;
import pl.dziewulskil.AplikacjaRestowa.requests.CurrencyInfoRequest;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final CurrencyMapper currencyMapper;
    private final NbpWebClient nbpWebClient;

    public ValueDto getCurrentCurrencyValue(CurrencyInfoRequest currencyInfoRequest) {
        List<CurrencyTableDto> currencyTableDtoList = nbpWebClient.getCurrencyTable();
        if (currencyTableDtoList != null && !currencyTableDtoList.isEmpty()) {
            CurrencyTableDto currencyTableDto = currencyTableDtoList.get(0);
            List<CurrencyRateDto> rates = currencyTableDto.rates();
            CurrencyRateDto currencyRateDto = rates.stream()
                    .filter(currencyRateDto1 -> currencyRateDto1.code().equals(currencyInfoRequest.getCode()))
                    .findFirst()
                    .orElseThrow(() -> new RestApplicationException("Code not found"));
            currencyRepository.save(currencyMapper.mapToCurrencyEntity(currencyInfoRequest, currencyRateDto));
            return currencyMapper.mapToValueDto(currencyRateDto);
        }
        return null;
    }

    public List<CurrencyEntityDto> getAllRequest() {
        List<CurrencyEntity> currencyEntities = currencyRepository.findAll();
        return currencyMapper.mapToCurrencyEntityDtos(currencyEntities);
    }
}
