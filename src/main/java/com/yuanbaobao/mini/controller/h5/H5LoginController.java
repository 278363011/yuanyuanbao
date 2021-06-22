package com.codebaobao.controller.h5;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class H5LoginController {
    @RequestMapping({"/h5/index","/h5","/h5/"})
    public String h5Index() {
        return "h5/index";
    }
}
