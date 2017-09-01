package com.epam.test_generator.entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Case implements Serializable{
    @Id
    @GeneratedValue
    private Long id;

    private CaseType type;

    private String feature;

    //Описание сценария
    private String scenario;

    @ManyToOne
    private Suit suit;

    public Case(Long id, String feature, String scenario, Suit suit) {
        this.id = id;
        this.feature = feature;
        this.scenario = scenario;
        this.suit = suit;
    }

    public Long getId() {
        return id;
    }

    public CaseType getType() {
        return type;
    }

    public void setType(CaseType type) {
        this.type = type;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    @Override
    public int hashCode() {
        return feature.hashCode() + 31 * scenario.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Case)) {
            return false;
        } else {
            String f = ((Case)o).getFeature();
            String s = ((Case)o).getScenario();
            if ( f.equals(feature) && s.equals(scenario)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "TestCase{" +
                "feature='" + feature + '\'' +
                ", scenario='" + scenario + '\'' +
                '}';
    }
}
