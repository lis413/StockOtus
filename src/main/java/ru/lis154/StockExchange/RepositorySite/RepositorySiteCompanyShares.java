package ru.lis154.StockExchange.RepositorySite;

import ru.lis154.StockExchange.Model.CompanySharesDTO;

public interface RepositorySiteCompanyShares {
    public CompanySharesDTO getCompanySharesDTO(String symbol);
}
