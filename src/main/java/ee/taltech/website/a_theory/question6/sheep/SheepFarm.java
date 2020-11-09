package ee.taltech.website.a_theory.question6.sheep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("sheep")
@RestController
@SpringBootApplication
public class SheepFarm {

    private final List<Sheep> sheepList = new ArrayList<>();
    private int indexCounter = 1;

    //todo for question 6 there are 4 assignments in total
    // Each person has to do only 1. So 2 person team has to do 2 different ones, 3 person - 3, 4 person - 4.
    // Make sure to commit under your user otherwise points won't count.
    // I didn't number these so you can pick your favorite

    //todo create a working api for a sheep farm
    // It compiles and runs. You need to use proper annotations, methods, etc, however to ease the process you can use empty methods (examples below).
    // Follow the story to have only the necessary methods in it

    //todo 1
    // Ireland.
    // Sheep.
    // Ireland. The land of the sheep.
    // My name's Liam. I have sheep. Many, many sheep.
    // What do you call a sheep covered in chocolate? - A Candy Baa.
    // Yeah, that's a very funny. Jokes aside.
    // I need a backoffice system for my sheep.
    // The sheep in Ireland are in flux. They come, they go.
    // There are new sheep coming here all the time. I need to store new sheep to the database.
    // And then there are sheep leaving. They run away. I remember all my sheep.
    // The ones that are not there I want to remove them from my database.
    // Each sheep has a unique collar with a special code. I've always numbered my sheep.
    // Special code is just a number. It's a basic incrementing number, but I call it special because I can.
    // I want to access sheep details, it's name, age, everything using that code.
    // That's it. Can you do that for me?

    //todo here are some examples of empty methods

    @PostMapping
    public Sheep saveSheep(@RequestBody Sheep sheep) {
        sheep.setId((long) (indexCounter));
        indexCounter ++;
        sheepList.add(sheep);
        return sheep;
    }

    @DeleteMapping("{id}")
    public void deleteSheep(@PathVariable Long id) {
        Optional<Sheep> foundSheep = sheepList.stream().filter(s -> s.getId().equals(id)).findAny();
        foundSheep.ifPresent(sheepList::remove);
    }

    @GetMapping("{id}")
     Optional<Sheep> getSheep(@PathVariable Long id) {
        return sheepList.stream().filter(s -> s.getId().equals(id)).findAny();
    }

    List<Sheep> emptyMethodReturnList(){
        return List.of();
    }

    Sheep emptyMethodReturn1(){
        return new Sheep();
    }

    void emptyMethodVoid(){
    }

}
