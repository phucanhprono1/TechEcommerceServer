package com.example.techecommerceserver.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String index(){
        return "index";
    }

    @GetMapping("/edit")
    public String edit(){
        return "edit";
    }

    @GetMapping("/list")
    public String list(){
        return "list";
    }
}
