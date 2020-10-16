package ee.taltech.website.a_theory.question6.sheep.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Sheep {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer age;
    private String color;

    public Sheep(Long id, String name, Integer age, String color) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public Sheep(String name, Integer age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public Sheep() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
