package ru.lis154.StockExchange.RepositoryDB;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.lis154.StockExchange.Model.CompanyDTO;

import java.util.List;

@Repository
public interface RepositoryDBCompanyDTO extends CrudRepository<CompanyDTO, Long> {
   // public void saveAll(List<CompanyDTO> companyDTOList);
}
