package ru.lis154.stockexchange.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import ru.lis154.stockexchange.entity.CompanyEntity;

@Data
@AllArgsConstructor
public class CompanyDto {
    public String symbol;
    public String exchange;
    public String exchangeSuffix;
    public String exchangeName;
    public String name;
    public String date;
    public String type;
    public String iexId;
    public String region;
    public String currency;
    public boolean isEnabled;
    public String figi;
    public String cik;
    public String lei;




    public CompanyEntity convertDTO(){
        return new CompanyEntity(this.getName(), this.getIexId(), this.getSymbol());
    }
}
