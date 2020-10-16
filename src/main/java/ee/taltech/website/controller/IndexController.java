package ee.taltech.website.controller;

import ee.taltech.website.model.Room;
import org.springframework.web.bind.annotation.*;

@RequestMapping("")
@RestController
public class IndexController {

    @GetMapping
    public String index(){
        return "Project Back!";
    }

    //todo 1.1 create some endpoint to return some number
    @GetMapping("number")
    public Integer someNumber(){
        return 123;
    }

    //todo 1.2 create some endpoint to return some object
    @GetMapping("room")
    public Room someRoom(){
        return new Room("Room 1", 1L);
    }

    //todo 1.3 create an endpoint "greeting", pass name to it to return "Hello <name>"
    @GetMapping("greeting")
    public String greeting(@RequestParam(required = false, defaultValue = "Oleg") String name,
                           @RequestParam(required = false) String job){
        return String.format("Hello %s you good %s", name, job);
    }

    @GetMapping("greeting/{name}")
    public String greeting(@PathVariable String name){
        return String.format("Hello for %s", name);
    }
}