package ru.lis154.stockotus.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lis154.stockotus.entity.CompanyEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
