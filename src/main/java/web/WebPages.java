package web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public final class WebPages {
    @RequestMapping(value = "/")
    public String form(final Model model) {
        return "form";
    }
}
