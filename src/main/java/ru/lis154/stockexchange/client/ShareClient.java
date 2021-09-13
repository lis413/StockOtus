package ru.lis154.stockexchange.client;

import ru.lis154.stockexchange.entity.ShareEntity;

import java.util.List;

public interface ShareClient {
    ShareEntity getShares(String symbol);

    List<ShareEntity> findHighestFiveCompanySharesDto();

    ShareEntity saveShare(ShareEntity shareEntity);
}
