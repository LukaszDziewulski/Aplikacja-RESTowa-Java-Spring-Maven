package pl.dziewulskil.AplikacjaRestowa.dtos;

import lombok.Builder;

@Builder
public record CurrencyRateDto(String currency,
                              String code,
                              double mid) {

    public CurrencyRateDto(double mid) {
        this(null, null, mid);
    }

    public CurrencyRateDto(String code) {
        this(null, code, 0);
    }
}
