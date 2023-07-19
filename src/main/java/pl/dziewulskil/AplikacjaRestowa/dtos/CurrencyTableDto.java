package pl.dziewulskil.AplikacjaRestowa.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CurrencyTableDto(@JsonProperty("rates") List<CurrencyRateDto> rates) {
}
