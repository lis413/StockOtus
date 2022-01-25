package ru.lis154.stockotus.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.lis154.stockotus.dto.CompanyDto;
import ru.lis154.stockotus.entity.CompanyEntity;
import ru.lis154.stockotus.entity.ShareEntity;
import ru.lis154.stockotus.repository.CompanyRepository;
import ru.lis154.stockotus.repository.ShareRepository;
import ru.lis154.stockotus.client.CompanyClient;
import ru.lis154.stockotus.client.ShareClient;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class CompanyJob {
    private final ExecutorService executorService;
    private final CompanyRepository companyRepository;
    private final CompanyClient companyClient;
    private final ShareClient shareClient;
    private final ShareRepository shareRepository;

    //@Scheduled(cron = "0 0/15 * * * ?")
    @Scheduled(fixedRate = 420000)
    public void getListCompanies() {
        List<CompanyDto> companies = companyClient.getCompany();
        List<ShareEntity> listShare;
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
        List<ShareEntity> listShareEntity = new CopyOnWriteArrayList<>();
                //new ArrayList<>();

        List<CompletableFuture> listCompletableFuture = companyEntityList.stream().map(x -> {
            CompletableFuture<Void> future =
                    CompletableFuture.
                    supplyAsync((() -> shareClient.getShares(x.getSymbol())), executorService)
                    .thenAcceptAsync(y -> listShareEntity.add(y));
                    return future;
        }).collect(Collectors.toList());

        for (CompletableFuture complitable : listCompletableFuture) {
            complitable.join();
        }
        return listShareEntity;
    }

    private void saveSharesChange(List<ShareEntity> listShare) {

        if (shareRepository.countEntity() != 0) {
            shareRepository.saveAll(getListShareForSave(listShare));
        } else {
            shareRepository.saveAll(listShare);
        }

    }


    private Map<String, ShareEntity> createHashMap(List<ShareEntity> shareEntities) {
        Map<String, ShareEntity> map = new HashMap<>();
        for (ShareEntity entity : shareEntities) {
            map.put(entity.getSymbol(), entity);
        }
        return map;
    }

    private List<ShareEntity> getListShareForSave(List<ShareEntity> listShare) {
        List<ShareEntity> newListShare = new ArrayList<>();
        List<ShareEntity> shareEntityListOld = shareRepository.findAll();
        shareRepository.deleteAll();
        HashMap<String, ShareEntity> oldMapShares = (HashMap<String, ShareEntity>) createHashMap(shareEntityListOld);
        HashMap<String, ShareEntity> newMapShares = (HashMap<String, ShareEntity>) createHashMap(listShare);
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
