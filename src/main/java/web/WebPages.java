package web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebPages {
    @RequestMapping(value = "/")
    public String form(Model model) {
        return "form";
    }
}
