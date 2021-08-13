package ru.lis154.StockExchange.RepositorySite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.lis154.StockExchange.Model.CompanyDTO;
import ru.lis154.StockExchange.RepositoryDB.RepositoryDBCompanyDTO;
import ru.lis154.StockExchange.RepositoryDB.RepositoryDBCompanyDTOImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RepositorySiteCompanyDTOImpl {
    private RestTemplate restTemplate = new RestTemplate();
    private String url = "https://sandbox.iexapis.com/stable/ref-data/symbols?token=Tpk_ee567917a6b640bb8602834c9d30e571";

    public List<CompanyDTO> getCompanyDTO() {

        List<CompanyDTO> list = new ArrayList<>();

        ResponseEntity<CompanyDTO[]> responseEntity =
                restTemplate.getForEntity(url, CompanyDTO[].class);
        CompanyDTO[] companyArray = responseEntity.getBody();
        return Arrays.stream(companyArray)
                // .map(Company::getName)
                .collect(Collectors.toList());




    }
}
