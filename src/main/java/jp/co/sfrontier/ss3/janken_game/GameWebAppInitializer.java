package jp.co.sfrontier.ss3.janken_game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class GameWebAppInitializer extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(GameWebAppInitializer.class, args);
  }

}