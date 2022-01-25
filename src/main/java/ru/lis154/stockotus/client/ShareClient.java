package ru.lis154.stockotus.client;

import ru.lis154.stockotus.entity.ShareEntity;

import java.util.List;

public interface ShareClient {
    ShareEntity getShares(String symbol);

    List<ShareEntity> findHighestFiveCompanySharesDto();

    ShareEntity saveShare(ShareEntity shareEntity);
}
