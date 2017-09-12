package com.epam.test_generator.entities;



import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
public class Case implements Serializable{

    @Id
    @GeneratedValue
    private Long id;

    private String description;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "parentCase")
    private List<Step> steps;

    @ManyToOne(cascade = CascadeType.MERGE, fetch= FetchType.EAGER)
    private Suit suit;

    private Date creationDate;

    private Integer priority;

    private String tags;



    public Case(){
    }

    public Case(Long id, String description, List<Step> steps, Suit suit, Integer priority, String tags) {
        this.id = id;
        this.description = description;
        this.steps = steps;
        this.suit = suit;
        this.priority = priority;
        this.tags = tags;
        this.creationDate = Calendar.getInstance().getTime();

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

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }


    public int getPriority() {
        return priority;
    }


    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Case{" +
                "feature='" + description + '\'' +
                ", scenario='" + steps + '\'' +
                '}';
    }
}