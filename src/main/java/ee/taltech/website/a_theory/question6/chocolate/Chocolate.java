package ee.taltech.website.a_theory.question6.chocolate;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequestMapping("cakes")
@RestController
@SpringBootApplication
public class Chocolate {

    private final List<Cake> dataBase = new ArrayList<>();


    //todo for question 6 there are 4 assignments in total
    // Each person has to do only 1. So 2 person team has to do 2 different ones, 3 person - 3, 4 person - 4.
    // Make sure to commit under your user otherwise points won't count.
    // I didn't number these so you can pick your favorite

    //todo create a working api for an cafe specializing in cakes
    // It compiles and runs. You need to use proper annotations, methods, etc, however to ease the process you can use empty methods (examples below).
    // Follow the story to have only the necessary methods in it

    //todo 1
    // Welcome-welcome!
    // My name is Lukas and I am very happy to meet you.
    // Thank you for coming to Vienna, Austria.
    // Vienna is the best place for coffee. And my cafe, Chocolate, is the best cafe for cakes.
    // You should try my Sachertorte, oh-ah, es isst so gut.
    // No-no, stop thinking we have no time for cake, now we must make an IT system.
    // I tell and you make.
    // I need a system to review my cakes. I have many cakes. I need a system.
    // Every cake must have following properties. Size: big/small. Sweetness: medium/sweet. These are mandatory as there must be ordnung.
    // In addition I want to search by ingredients and toppings, these are not necessary.
    // I strive for perfection.
    // Every day I bake a new cake. I must add it to the cakes. Sell it to my loyal customers.
    // I also make my cakes better. I am an artist and I improve my recipes.
    // I take existing Sachertorte and I make it better and next week I make it better and next week...
    // Can you do this? I need this system tomorrow!


    //todo here are some examples of empty methods

    @GetMapping
    public List<Cake> getCakes() {
        return dataBase;
    }


    @GetMapping("{topping}")
    public Optional<List<Cake>> getCakesByToppings(@RequestParam String topping) {
        List<Cake> cakes = new ArrayList<>();
        for (Cake cake : dataBase) {
            for (String topping1 : cake.getToppings()) {
                if (topping1.equals(topping)) {
                    cakes.add(cake);
                }
            }
        }
        return Optional.of(cakes);
    }

    @GetMapping("{ingredient}")
    public Optional<List<Cake>> getCakesByIngredients(@RequestParam String ingredient) {
        List<Cake> cakes = new ArrayList<>();
        for (Cake cake : dataBase) {
            for (String ingredient1 : cake.getIngredients()) {
                if (ingredient1.equals(ingredient)) {
                    cakes.add(cake);
                }
            }
        }
        return Optional.of(cakes);
    }

    @PostMapping
    public Cake saveCake(@RequestBody Cake cake) {
        dataBase.add(cake);
        return cake;
    }

    @PutMapping
    public Cake updateCake(@RequestBody Cake cake) {
        for (Cake cake1 : dataBase) {
            if (cake1.getId().equals(cake.getId())) {
                cake1.setIngredients(cake.getIngredients());
                cake1.setSweetness(cake.getSweetness());
                cake1.setToppings(cake.getToppings());
                cake1.setSize(cake.getSize());
            }
        }
        return cake;
    }

    List<Cake> emptyMethodReturnList() {
        return List.of();
    }

    Cake emptyMethodReturn1() {
        return new Cake();
    }

    void emptyMethodVoid() {
    }
}
