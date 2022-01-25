package ru.lis154.stockotus.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lis154.stockotus.entity.CompanyEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    private String symbol;
    private String exchange;
    private String exchangeSuffix;
    private String exchangeName;
    private String name;
    private String date;
    private String type;
    private String iexId;
    private String region;
    private String currency;
    private boolean isEnabled;
    private String figi;
    private String cik;
    private String lei;



    public CompanyEntity convertDTO(){
        return new CompanyEntity(this.getName(), this.getIexId(), this.getSymbol());
    }
}
