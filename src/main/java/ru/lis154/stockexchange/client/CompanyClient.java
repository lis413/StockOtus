package ru.lis154.stockexchange.client;


import ru.lis154.stockexchange.dto.CompanyDto;

import java.util.List;

public interface CompanyClient {
    List<CompanyDto> getCompany();
}
