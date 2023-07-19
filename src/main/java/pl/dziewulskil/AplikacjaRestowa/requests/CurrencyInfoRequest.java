package pl.dziewulskil.AplikacjaRestowa.requests;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Value
@Builder
public class CurrencyInfoRequest {

    @NotEmpty
    @Size(min = 3, max = 20)
    String firstname;

    @NotEmpty
    @Size(min = 3, max = 50)
    String lastname;

    @NotEmpty
    @Size(min = 3, max = 3)
    String code;
}
