package ru.lis154.StockExchange.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@Table (name="CompanyDTO")
public class CompanyDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private String iexId;
    @Column
    public String symbol;

    public CompanyDTO(String name, String iexId, String symbol) {
        this.name = name;
        this.iexId = iexId;
        this.symbol = symbol;
    }

    public CompanyDTO(){}
}
