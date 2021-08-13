package ru.lis154.StockExchange.RepositorySite;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.lis154.StockExchange.Model.CompanyShares;
import ru.lis154.StockExchange.Model.CompanySharesDTO;

@Component
public class RepositorySiteCompanynSharesImpl implements RepositorySiteCompanyShares {
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public CompanySharesDTO getCompanySharesDTO(String symbol) {
        CompanySharesDTO companySharesDTO;
        String url = "https://sandbox.iexapis.com/stable/stock/" + symbol + "/quote?token=Tpk_ee567917a6b640bb8602834c9d30e571";
       try {
           ResponseEntity<CompanyShares> responseEntity =
                   restTemplate.getForEntity(url, CompanyShares.class);
           CompanyShares companyShares = responseEntity.getBody();

           return new CompanySharesDTO(companyShares.getSymbol(), companyShares.getCompanyName(), companyShares.getLatestPrice(), companyShares.getPreviousVolume(), companyShares.getVolume());
       } catch (HttpClientErrorException e) {
          // System.out.println("404 not found");
           return new CompanySharesDTO();
       }
    }
}
