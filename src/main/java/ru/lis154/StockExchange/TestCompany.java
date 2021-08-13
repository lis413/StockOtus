package ru.lis154.StockExchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.lis154.StockExchange.Model.Company;
import ru.lis154.StockExchange.Model.CompanyDTO;
import ru.lis154.StockExchange.Model.CompanySharesDTO;
import ru.lis154.StockExchange.RepositoryDB.RepositoryDBCompanyDTO;
import ru.lis154.StockExchange.RepositoryDB.RepositoryDBCompanyDTOImpl;
import ru.lis154.StockExchange.RepositoryDB.RepositoryDBCompanyShares;
import ru.lis154.StockExchange.RepositorySite.RepositorySiteCompany;
import ru.lis154.StockExchange.RepositorySite.RepositorySiteCompanyImpl;
import ru.lis154.StockExchange.RepositorySite.RepositorySiteCompanyShares;
import ru.lis154.StockExchange.RepositorySite.RepositorySiteCompanynSharesImpl;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Component
public class TestCompany {
    @Autowired
    RepositoryDBCompanyDTO repositoryDBCompanyDTO;

    @Autowired
    RepositoryDBCompanyShares repositoryDBCompanyShares;
    @Autowired
    RepositorySiteCompany repositoryCompany;

    @Autowired
    RepositorySiteCompanyShares repositorySiteCompanyShares;
    public void testCompany() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
      //  RepositorySiteCompany repositoryCompany = new RepositorySiteCompanyImpl();
        List<Company> companies = repositoryCompany.getCompany();
        System.out.println(companies.size());
        List<CompanyDTO> listDTO = companies.stream().map(Company::convertDTO).collect(Collectors.toList());
        repositoryDBCompanyDTO.saveAll(listDTO);
        System.out.println("finish save company");
     //   RepositorySiteCompanyShares repositorySiteCompanyShares = new RepositorySiteCompanynSharesImpl();
        for (CompanyDTO comp : listDTO) {
            executorService.execute(() -> repositoryDBCompanyShares.save(repositorySiteCompanyShares.getCompanySharesDTO(comp.getSymbol())));
        }
        executorService.shutdown();
    }
}
