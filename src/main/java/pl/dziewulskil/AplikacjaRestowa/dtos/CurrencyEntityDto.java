package pl.dziewulskil.AplikacjaRestowa.dtos;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CurrencyEntityDto {
    String firstname;
    String lastname;
    String code;
    String currency;
    double value;
    LocalDateTime date;
}
