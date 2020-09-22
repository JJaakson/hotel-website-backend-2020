package ee.taltech.website.controller;

import ee.taltech.website.model.Item;
import ee.taltech.website.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("test")
@RestController
public class TestController {

    @Autowired
    private EntityRepository entityRepository;

    @GetMapping
    public List<Item> getEntities(@RequestParam(value = "name", required = false) String name) {
        return entityRepository.findAll();
    }
}
