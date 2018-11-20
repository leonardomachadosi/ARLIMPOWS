package br.ufma.lsdi.configuration;

import br.ufma.lsdi.controller.SimulatorCarbonoService;
import br.ufma.lsdi.controller.SimulatorNitrogenioService;
import br.ufma.lsdi.controller.SimulatorPM25Service;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@EnableScheduling
@EnableFeignClients(basePackages = {"br.ufma.lsdi"})
@SpringBootApplication(scanBasePackages = {"br.ufma.lsdi"})
public class ArLimpoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ArLimpoApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ArLimpoApplication.class);
    }

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        ThreadPoolTaskScheduler threadPoolTaskScheduler
                = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(200);
        threadPoolTaskScheduler.setThreadNamePrefix(
                "ThreadPoolTaskScheduler");
        return threadPoolTaskScheduler;
    }



    @Bean
    public CommandLineRunner run()  {
        return args -> {
            //SimulatorCarbonoService simulatorService = new SimulatorCarbonoService();
            //SimulatorNitrogenioService simulatorNitrogenioService = new SimulatorNitrogenioService();
            SimulatorPM25Service simulatorPM25Service = new SimulatorPM25Service();
        };
    }
}