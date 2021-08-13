package ru.lis154.StockExchange.Model;


import lombok.Data;

@Data
public class Company {
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

    public CompanyDTO convertDTO(){
        return new CompanyDTO(this.getName(), this.getIexId(), this.getSymbol());
    }
}
