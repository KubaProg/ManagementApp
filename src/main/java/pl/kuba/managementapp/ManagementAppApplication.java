package pl.kuba.managementapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.kuba.managementapp.JobCycle.JobCycleRepository;

import java.beans.beancontext.BeanContext;
import java.util.Locale;

@SpringBootApplication
public class ManagementAppApplication {

    public static void main(String[] args)
    {
        Locale.setDefault(Locale.US);
        SpringApplication.run(ManagementAppApplication.class, args);
    }

}
