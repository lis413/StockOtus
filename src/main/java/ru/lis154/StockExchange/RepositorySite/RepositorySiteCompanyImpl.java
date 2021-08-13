package ru.lis154.StockExchange.RepositorySite;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.lis154.StockExchange.Model.Company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RepositorySiteCompanyImpl implements RepositorySiteCompany {
    private RestTemplate restTemplate = new RestTemplate();
    private String url = "https://sandbox.iexapis.com/stable/ref-data/symbols?token=Tpk_ee567917a6b640bb8602834c9d30e571";
    @Override
    public List<Company> getCompany() {

        List<Company> list = new ArrayList<>();

        ResponseEntity<Company[]> responseEntity =
                restTemplate.getForEntity(url, Company[].class);
        Company[] companyArray = responseEntity.getBody();
        return Arrays.stream(companyArray)
               // .map(Company::getName)
                .collect(Collectors.toList());

    }
}
