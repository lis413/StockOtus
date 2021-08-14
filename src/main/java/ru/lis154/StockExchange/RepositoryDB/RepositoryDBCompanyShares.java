package ru.lis154.StockExchange.RepositoryDB;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.lis154.StockExchange.Model.CompanyShares;
import ru.lis154.StockExchange.Model.CompanySharesDTO;

import java.util.List;

public interface RepositoryDBCompanyShares extends CrudRepository<CompanySharesDTO, Long> {
    public CompanySharesDTO findBySymbol(String symbol);

    @Query(
            value = "select * from company_sharesdto order by previous_volume desc limit 5",
            nativeQuery = true)
    List<CompanyShares> findHighestFiveCompanySharesDto();
}
