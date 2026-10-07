// Folie 213 – Controller
// View "greeting" -> src/slides/resources/templates/greeting.html (Folie 211)
package com.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public ModelAndView greeting(
            @RequestParam(name = "name", defaultValue = "World") String name
    ) {
        var mv = new ModelAndView("greeting");
        mv.addObject("name", name);
        return mv;
    }
}
