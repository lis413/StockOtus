package ru.lis154.stockotus.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
//@AllArgsConstructor
@Table (name="companies")
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //@Id
    @Column
    private String name;
    @Column
    private String iexId;
    @Column
    public String symbol;

    public CompanyEntity(String name, String iexId, String symbol) {
        this.name = name;
        this.iexId = iexId;
        this.symbol = symbol;
    }

    public CompanyEntity(){}
}
