package me.sbio.readyourtweets.app.controller;

import me.sbio.readyourtweets.app.handler.HelloHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

    @Autowired
    private HelloHandler helloHandler;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ModelAndView sayHello(@RequestParam(value = "name", required = false) String name) {
        return helloHandler.sayHello(name);
    }
}
