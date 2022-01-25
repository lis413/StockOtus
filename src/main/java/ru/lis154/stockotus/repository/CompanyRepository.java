package ru.lis154.stockotus.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.lis154.stockotus.entity.CompanyEntity;

@Repository
public interface CompanyRepository extends CrudRepository<CompanyEntity, Long> {
  //  void setAllCompanyEntityBySymbol(List<CompanyEntity> list);

    @Query(value = "select count(symbol) from companies", nativeQuery = true)
    int countEntity();

    void deleteAll();
}
