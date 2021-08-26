package ru.lis154.stockexchange.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.MethodExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.lis154.stockexchange.dto.CompanyDto;
import ru.lis154.stockexchange.entity.CompanyEntity;
import ru.lis154.stockexchange.dto.ShareDto;
import ru.lis154.stockexchange.entity.ShareEntity;
import ru.lis154.stockexchange.repository.CompanyRepository;
import ru.lis154.stockexchange.repository.ShareRepository;
import ru.lis154.stockexchange.service.CompanyService;
import ru.lis154.stockexchange.service.ShareService;

import javax.swing.text.html.parser.Entity;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class CompanyJob {
    private final ExecutorService executorService;
    private final CompanyRepository companyRepository;
    private final CompanyService companyService;
    private final ShareService shareService;
    private final ShareRepository shareRepository;

    //@Scheduled(cron = "0 0/15 * * * ?")
    @Scheduled(fixedRate = 420000)
    public void getCompanies() {
        List<CompanyDto> companies = companyService.getCompany();
        List<ShareEntity> listShare;// = new ArrayList<>();
        List<CompanyEntity> companyEntitiesList = companies.stream().map(CompanyDto::convertDTO).collect(Collectors.toList());
        companyRepository.deleteAll();
        companyRepository.saveAll(companyEntitiesList);

        listShare = getSharesOnAPI(companyEntitiesList);
        saveSharesChange(listShare);
        System.out.println("testtesttest");
        List<ShareEntity> fiveCompanyShares = shareRepository.findHighestFiveCompanySharesDto();
        for (ShareEntity five : fiveCompanyShares) {
            log.info(five.toString());
        }
    }

    private List<ShareEntity> getSharesOnAPI(List<CompanyEntity> companyEntityList) {
        List<ShareEntity> listShare = new ArrayList<>();
        List<CompletableFuture> streamList = companyEntityList.stream()
                .map(x -> {
                    CompletableFuture<ShareEntity> future = CompletableFuture.
                            supplyAsync((() -> shareService.getShares(x.getSymbol())), executorService);
                    return future;
                }).collect(Collectors.toList());
        for (CompletableFuture complitable : streamList) {
            //executorService.submit(() -> listShare.add((ShareEntity) complitable.join()));
            listShare.add((ShareEntity) complitable.join());
        }
        return listShare;
    }

    private void saveSharesChange(List<ShareEntity> listShare) {

        if (shareRepository.countEntity() != 0) {
            shareRepository.saveAll(getListShareForSave(listShare));
        } else {
            shareRepository.saveAll(listShare);
        }

    }


    private HashMap<String, ShareEntity> createHashMap(List<ShareEntity> shareEntities) {
        HashMap<String, ShareEntity> map = new HashMap<>();
        for (ShareEntity entity : shareEntities) {
            map.put(entity.getSymbol(), entity);
        }
        return map;
    }

    private List<ShareEntity> getListShareForSave(List<ShareEntity> listShare) {
        List<ShareEntity> newListShare = new ArrayList<>();
        List<ShareEntity> shareEntityListOld = shareRepository.findAll();
        shareRepository.deleteAll();
        HashMap<String, ShareEntity> oldMapShares = createHashMap(shareEntityListOld);
        HashMap<String, ShareEntity> newMapShares = createHashMap(listShare);
        for (String symbol : newMapShares.keySet()) {
            if (oldMapShares.containsKey(symbol)) {
                ShareEntity share = newMapShares.get(symbol);
                share.setDifferent(share.getLatestPrice() - oldMapShares.get(symbol).getLatestPrice());
                newListShare.add(share);
            } else {
                newListShare.add(newMapShares.get(symbol));
            }
        }
        return newListShare;
    }
}
