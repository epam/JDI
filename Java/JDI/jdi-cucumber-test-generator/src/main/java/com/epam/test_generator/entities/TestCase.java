package com.epam.test_generator.entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class TestCase implements Serializable{
    @Id
    @GeneratedValue
    private Long id;


    private TestCaseType type;

    private String feature;

    //Описание сценария
    private String scenario;

    @ManyToOne
    private TestSuit testSuit;

    public TestCase(Long id, String feature, String scenario, TestSuit testSuit) {
        this.id = id;
        this.feature = feature;
        this.scenario = scenario;
        this.testSuit = testSuit;
    }

    public TestCaseType getType() {
        return type;
    }

    public void setType(TestCaseType type) {
        this.type = type;
    }

    public TestSuit getTestSuit() {
        return testSuit;
    }

    public void setTestSuit(TestSuit testSuit) {
        this.testSuit = testSuit;
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

    public Long getId() {
        return id;
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

        if (!(o instanceof TestCase)) {
            return false;
        } else {
            String f = ((TestCase)o).getFeature();
            String s = ((TestCase)o).getScenario();
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
