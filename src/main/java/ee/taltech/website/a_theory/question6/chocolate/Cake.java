package ee.taltech.website.a_theory.question6.chocolate;

import java.util.List;

public class Cake {

    private Long id;
    private String size;
    private String sweetness;
    private List<String> ingredients;
    private List<String> toppings;

    public Cake(Long id, String size, String sweetness, List<String> ingredients, List<String> toppings) {
        this.id = id;
        this.size = size;
        this.sweetness = sweetness;
        this.ingredients = ingredients;
        this.toppings = toppings;
    }

    public Cake(String size, String sweetness, List<String> ingredients, List<String> toppings) {
        this.size = size;
        this.sweetness = sweetness;
        this.ingredients = ingredients;
        this.toppings = toppings;
    }

    public Cake() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSweetness() {
        return sweetness;
    }

    public void setSweetness(String sweetness) {
        this.sweetness = sweetness;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getToppings() {
        return toppings;
    }

    public void setToppings(List<String> toppings) {
        this.toppings = toppings;
    }
}
