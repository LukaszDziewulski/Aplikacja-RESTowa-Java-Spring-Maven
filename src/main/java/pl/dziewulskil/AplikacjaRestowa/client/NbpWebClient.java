package pl.dziewulskil.AplikacjaRestowa.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import pl.dziewulskil.AplikacjaRestowa.dtos.CurrencyTableDto;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NbpWebClient {

    private final WebClient webClient;

    public List<CurrencyTableDto> getCurrencyTable() {
        return webClient.get()
                .uri("http://api.nbp.pl/api/exchangerates/tables/A?format=json")
                .retrieve()
                .bodyToFlux(CurrencyTableDto.class)
                .collectList()
                .block();
    }
}
