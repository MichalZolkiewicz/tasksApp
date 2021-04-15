package com.crud.tasks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@CrossOrigin(origins = "*")
@Controller
public class StaticWebPageController {

    public String index(Map<String, Object> model) {
        model.put("variable", "My Thymeleaf variable");
        model.put("one", 1);
        model.put("two", 2);
        model.put("plus", '+');
        model.put("minus", '-');
        model.put("asterisk", '*');
        model.put("equals", '=');
        return "index";
    }
}
