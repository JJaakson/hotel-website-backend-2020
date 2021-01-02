package ee.taltech.website.controller;

import ee.taltech.website.security.Roles;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("")
@RestController
public class IndexController {

    @GetMapping
    public String index(){
        return "API is up";
    }

    @Secured(Roles.USER)
    @GetMapping("user")
    public String user(){
        return "User url";
    }

    @Secured(Roles.ADMIN)
    @GetMapping("admin")
    public String admin(){
        return "Admin url";
    }
}
