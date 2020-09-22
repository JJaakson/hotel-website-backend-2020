package ee.taltech.website.controller;

import ee.taltech.website.model.Item;
import ee.taltech.website.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("test")
@RestController
public class TestController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping
    public List<Item> getEntities(@RequestParam(value = "name", required = false) String name) {
        return itemRepository.findAll();
    }
}
