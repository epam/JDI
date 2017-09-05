package com.epam.test_generator.entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Case implements Serializable{

    @Id
    @GeneratedValue
    private Long id;

    private String description;

    //Описание сценария
    private String steps;

    @ManyToOne(cascade = {CascadeType.MERGE},fetch= FetchType.EAGER)
    private Suit suit;

    public Case(){
    }

    public Case(Long id, String description, String steps, Suit suit) {
        this.id = id;
        this.description = description;
        this.steps = steps;
        this.suit = suit;
    }

    public Long getId() {
        return id;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    @Override
    public int hashCode() {
        return description.hashCode() + 31 * steps.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Case)) {
            return false;
        } else {
            String f = ((Case)o).getDescription();
            String s = ((Case)o).getSteps();

            if ( f.equals(description) && s.equals(steps)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Case{" +
                "feature='" + description + '\'' +
                ", scenario='" + steps + '\'' +
                '}';
    }
}