package ru.lis154.stockexchange.service;

import ru.lis154.stockexchange.entity.ShareEntity;

import java.util.List;

public interface ShareService {
    ShareEntity getShares(String symbol);

    List<ShareEntity> findHighestFiveCompanySharesDto();

    ShareEntity saveShare(ShareEntity shareEntity);
}
