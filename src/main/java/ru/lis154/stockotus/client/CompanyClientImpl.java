package ru.lis154.stockotus.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.lis154.stockotus.config.AppConfig;
import ru.lis154.stockotus.dto.CompanyDto;
import ru.lis154.stockotus.senderSqs.MessageSender;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CompanyClientImpl implements CompanyClient {
    private final RestTemplate restTemplate;
    private static AppConfig config = new AppConfig();
    private static final String url = config.getUrlForCompany() + config.getTokenForUrl();
    @Autowired
    private MessageSender messageSender;

    @Override
    public List<CompanyDto> getCompany() {
        ResponseEntity<CompanyDto[]> responseEntity  =
                restTemplate.getForEntity(url, CompanyDto[].class);
        CompanyDto[] companyEntityArray = responseEntity.getBody();

        if(Objects.isNull(companyEntityArray)) {
            messageSender.send("Error saved", "NEW", "ERROR");
            return  Collections.emptyList();
        }
        messageSender.send("Successfully saved","NEW", "INFO");
        return Arrays.stream(companyEntityArray)
                .collect(Collectors.toList());
    }
}
