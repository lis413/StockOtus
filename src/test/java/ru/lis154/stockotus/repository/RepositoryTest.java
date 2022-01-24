package ru.lis154.stockotus.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.lis154.stockotus.dto.CompanyDto;
import ru.lis154.stockotus.entity.CompanyEntity;
import ru.lis154.stockotus.entity.ShareEntity;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RepositoryTest {

    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    ShareRepository shareRepository;

    @Test
    public void contextLoads() {
        Assertions.assertNotNull(shareRepository);
        Assertions.assertNotNull(companyRepository);
    }

    @Test
    void saveAndGetCompany(){
        CompanyEntity companyEntity = new CompanyEntity("5", "8", "1");
        companyRepository.save(companyEntity);
        CompanyEntity companyEntity2 = companyRepository.findByName("5");
        companyRepository.deleteByIexId("8");
        Assertions.assertEquals(companyEntity, companyEntity2);
    }

    @Test
    void saveAndGetShare(){
        ShareEntity shareEntity = new ShareEntity("1", "2", 2.0, 2, 3, 5.0);
        shareRepository.save(shareEntity);
        ShareEntity shareEntity1 = shareRepository.findBySymbol("1");
        shareRepository.deleteByCompanyName("2");
        Assertions.assertEquals(shareEntity, shareEntity1);
    }
}
