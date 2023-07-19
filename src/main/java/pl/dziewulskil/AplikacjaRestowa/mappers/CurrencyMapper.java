package pl.dziewulskil.AplikacjaRestowa.mappers;

import org.springframework.stereotype.Component;
import pl.dziewulskil.AplikacjaRestowa.dtos.CurrencyEntityDto;
import pl.dziewulskil.AplikacjaRestowa.dtos.CurrencyRateDto;
import pl.dziewulskil.AplikacjaRestowa.dtos.ValueDto;
import pl.dziewulskil.AplikacjaRestowa.entity.CurrencyEntity;
import pl.dziewulskil.AplikacjaRestowa.requests.CurrencyInfoRequest;

import java.util.List;

@Component
public class CurrencyMapper {

    public ValueDto mapToValueDto(CurrencyRateDto currencyRateDto) {
        return ValueDto.builder()
                .value(currencyRateDto.getMid())
                .build();
    }

    public CurrencyEntity mapToCurrencyEntity(CurrencyInfoRequest currencyInfoRequest, CurrencyRateDto currencyRateDto) {
        return CurrencyEntity.builder()
                .firstname(currencyInfoRequest.getFirstname())
                .lastname(currencyInfoRequest.getLastname())
                .code(currencyInfoRequest.getCode())
                .currency(currencyRateDto.getCurrency())
                .value(currencyRateDto.getMid())
                .build();
    }

    public List<CurrencyEntityDto> mapToCurrencyEntityDtos(List<CurrencyEntity> currencyEntities) {
        return currencyEntities.stream()
                .map(this::mapToCurrencyEntityDto)
                .toList();
    }

    private CurrencyEntityDto mapToCurrencyEntityDto(CurrencyEntity currencyEntity) {
        return CurrencyEntityDto.builder()
                .firstname(currencyEntity.getFirstname())
                .lastname(currencyEntity.getLastname())
                .code(currencyEntity.getCode())
                .currency(currencyEntity.getCurrency())
                .value(currencyEntity.getValue())
                .date(currencyEntity.getDate())
                .build();
    }
}
