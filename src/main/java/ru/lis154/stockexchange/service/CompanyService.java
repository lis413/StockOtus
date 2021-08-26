package ru.lis154.stockexchange.service;


import ru.lis154.stockexchange.dto.CompanyDto;

import java.util.List;

public interface CompanyService {
    List<CompanyDto> getCompany();
}
