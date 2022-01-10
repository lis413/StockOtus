package ru.lis154.stockotus.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.lis154.stockotus.config.AppConfig;
import ru.lis154.stockotus.dto.ShareDto;
import ru.lis154.stockotus.entity.ShareEntity;
import ru.lis154.stockotus.repository.ShareRepository;

import java.util.List;
import java.util.Objects;


@Slf4j
@Component
@RequiredArgsConstructor
public class ShareClientImpl implements ShareClient {
    private static final AppConfig config = new AppConfig();
    private static final String url = config.getUrlForShares() +  config.getTokenForUrl();

    private final RestTemplate restTemplate;
    private final ShareRepository shareRepository;

    @Override
    public ShareEntity getShares(String symbol) {
        String symbolUrl = String.format(url, symbol);
        try {
            ResponseEntity<ShareDto> responseEntity =
                    restTemplate.getForEntity(symbolUrl, ShareDto.class);
            ShareDto companyShares = responseEntity.getBody();

            if(Objects.isNull(companyShares)) {
                return new ShareEntity();
            }

            return new ShareEntity(companyShares.getSymbol(), companyShares.getCompanyName(), companyShares.getLatestPrice(), companyShares.getPreviousVolume(), companyShares.getVolume());
        } catch (HttpClientErrorException e) {
            log.error("{} not found int CompanyShares", symbol);
            return new ShareEntity(symbol);
        }
    }

    @Override
    public List<ShareEntity> findHighestFiveCompanySharesDto() {
        return shareRepository.findHighestFiveCompanySharesDto();
    }

    @Override
    public ShareEntity saveShare(ShareEntity shareEntity) {
        return shareRepository.save(shareEntity);
    }
}
