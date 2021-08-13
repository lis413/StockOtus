package ru.lis154.StockExchange.RepositoryDB;

import org.springframework.data.repository.CrudRepository;
import ru.lis154.StockExchange.Model.CompanySharesDTO;

public interface RepositoryDBCompanyShares extends CrudRepository<CompanySharesDTO, Long> {
    public CompanySharesDTO findBySymbol(String symbol);
}
