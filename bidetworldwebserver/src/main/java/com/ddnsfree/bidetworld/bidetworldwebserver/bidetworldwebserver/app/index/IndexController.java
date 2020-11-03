package com.ddnsfree.bidetworld.bidetworldwebserver.bidetworldwebserver.app.index;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    public IndexController() {
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {

    }

    @ModelAttribute
    public IndexForm setUpIndexForm() {
        return new IndexForm();
    }

    @RequestMapping(value = "index")
    public String displayIndex(Model model) {
        model.addAttribute("", "attributeValue");
        return "index";
    }
}
