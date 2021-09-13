package ru.lis154.stockexchange.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.lis154.stockexchange.config.AppConfig;
import ru.lis154.stockexchange.dto.CompanyDto;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CompanyClientImpl implements CompanyClient {
    private final RestTemplate restTemplate;
    private static AppConfig config = new AppConfig();
    private static final String url = config.getUrlForCompany() + config.getTokenForUrl();

    @Override
    public List<CompanyDto> getCompany() {
        ResponseEntity<CompanyDto[]> responseEntity =
                restTemplate.getForEntity(url, CompanyDto[].class);
        CompanyDto[] companyEntityArray = responseEntity.getBody();

        if(Objects.isNull(companyEntityArray)) {
            return Collections.emptyList();
        }
        return Arrays.stream(companyEntityArray)
                .collect(Collectors.toList());
    }
}
