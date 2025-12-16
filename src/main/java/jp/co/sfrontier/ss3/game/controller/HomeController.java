package jp.co.sfrontier.ss3.game.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/ping")
    public String ping() {
        return "OK";
    }
}
