package com.emse.spring.faircorp.hello;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration//First, annotate this class to mark it as a configuration bean
public class FaircorpApplicationConfig {

    @Bean//Add annotation to say that this method return a new Bean Spring
    public CommandLineRunner greetingCommandLine(GreetingService greetingService){
        return new CommandLineRunner(){
            @Override
            public void run(String... args) throws Exception {
                greetingService.greet("Spirng");
            }
        };
    }
}
