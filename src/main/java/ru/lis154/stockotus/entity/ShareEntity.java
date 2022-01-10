package ru.lis154.stockotus.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shares")
public class ShareEntity implements Comparable<ShareEntity> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
   // @Id
    @Column(name = "symbol")
    public String symbol;
    @Column(name = "company_name")
    public String companyName;
    @Column(name = "latest_price")
    public double latestPrice;
    @Column(name = "previous_volume")
    public Integer previousVolume;
    @Column(name = "volume")
    public Integer volume;
    @Column(name = "different")
    public Double different;

    public ShareEntity(String symbol, String companyName, double latestPrice, int previousVolume, int volume, Double different) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.latestPrice = latestPrice;
        this.previousVolume = previousVolume;
        this.volume = volume;
        this.different = different;
    }

    public ShareEntity(String symbol, String companyName, double latestPrice, int previousVolume, int volume) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.latestPrice = latestPrice;
        this.previousVolume = previousVolume;
        this.volume = volume;
        this.different = 0.0;
    }

    public ShareEntity(String symbol){
        this.symbol = symbol;
    }


    @Override
    public int compareTo(ShareEntity o) {
        return this.getSymbol().compareTo(o.getSymbol());
    }
}
