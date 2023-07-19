package pl.dziewulskil.AplikacjaRestowa.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.dziewulskil.AplikacjaRestowa.dtos.CurrencyEntityDto;
import pl.dziewulskil.AplikacjaRestowa.dtos.ValueDto;
import pl.dziewulskil.AplikacjaRestowa.requests.CurrencyInfoRequest;
import pl.dziewulskil.AplikacjaRestowa.services.CurrencyService;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CurrencyValueController {

    private final CurrencyService currencyService;

    @PostMapping("/currencies/get-current-currency-value-command")
    @ResponseStatus(HttpStatus.OK)
    public ValueDto getCurrentCurrencyValue(@Valid @RequestBody CurrencyInfoRequest currencyInfoRequest) {
        return currencyService.getCurrentCurrencyValue(currencyInfoRequest);
    }

    @GetMapping("/currencies/requests")
    @ResponseStatus(HttpStatus.OK)
    public List<CurrencyEntityDto> getAllRequest() {
        return currencyService.getAllRequest();
    }
}