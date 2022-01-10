package ru.lis154.stockotus.client;


import ru.lis154.stockotus.dto.CompanyDto;

import java.util.List;

public interface CompanyClient {
    List<CompanyDto> getCompany();
}
