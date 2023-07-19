package pl.dziewulskil.AplikacjaRestowa.dtos;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ValueDto {
    double value;
}
