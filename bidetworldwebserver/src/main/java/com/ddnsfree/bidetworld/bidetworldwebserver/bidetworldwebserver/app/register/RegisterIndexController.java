package com.ddnsfree.bidetworld.bidetworldwebserver.bidetworldwebserver.app.register;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterIndexController {

    public RegisterIndexController() {
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {

    }

    @ModelAttribute
    public RegisterForm setRegisterForm() {
        return new RegisterForm();
    }

    @RequestMapping(value = "register-index")
    public String displayRegisterIndex(Model model) {
        model.addAttribute("", "attributeValue");
        return "register/register_index";
    }
}
