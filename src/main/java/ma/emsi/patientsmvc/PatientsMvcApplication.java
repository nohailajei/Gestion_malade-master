package ma.emsi.patientsmvc;

import ma.emsi.patientsmvc.entities.Patient;
import ma.emsi.patientsmvc.repositories.PatientRepository;
import ma.emsi.patientsmvc.sec.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class PatientsMvcApplication {

    public static void main(String[] args) {

        SpringApplication.run(PatientsMvcApplication.class, args);
    }

    //@Bean //cad que cette méth va s'executer au démarrage.
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {
            patientRepository.save(
                    new Patient(null,"Assia",new Date(),false,122));
            patientRepository.save(
                    new Patient(null,"Zineb",new Date(),true,321));
            patientRepository.save(
                    new Patient(null,"oumaima",new Date(),true,165));
            patientRepository.save(
                    new Patient(null,"oualid",new Date(),false,132));

            patientRepository.findAll().forEach(p->{
                System.out.println(p.getNom());
            });

        };
    }

    @Bean  //cad au démarrage créer un objet passwordencoder
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    //@Bean
    CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {
            securityService.saveNewUser("nohaila","1234","1234");
            securityService.saveNewUser("aya","1234","1234");
            securityService.saveNewUser("zineb","1234","1234");

            securityService.saveNewRole("USER","");
            securityService.saveNewRole("ADMIN","");

            securityService.addRoleToUser("nohaila","USER");
            securityService.addRoleToUser("nohaila","ADMIN");
            securityService.addRoleToUser("aya","USER");
            securityService.addRoleToUser("zineb","USER");
        };
    }

}
