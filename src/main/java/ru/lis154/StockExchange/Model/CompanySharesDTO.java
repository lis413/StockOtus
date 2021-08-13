package ru.lis154.StockExchange.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@AllArgsConstructor
@Table(name = "CompanySharesDTO")
public class CompanySharesDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    public String symbol;
    @Column
    public String companyName;
    @Column
    public double latestPrice;
    @Column
    public int previousVolume;
    @Column
    public int volume;

    public CompanySharesDTO(String symbol, String companyName, double latestPrice, int previousVolume, int volume) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.latestPrice = latestPrice;
        this.previousVolume = previousVolume;
        this.volume = volume;
    }

    public CompanySharesDTO() {
    }
}
