package ma.enset.uni;

import ma.enset.uni.Repository.PatientRepository;
import ma.enset.uni.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class UniApplication implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(UniApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        patientRepository.save(new Patient(null,"pat1",new Date(),false,100));
        patientRepository.save(new Patient(null,"pat2",new Date(),false,200));
        patientRepository.save(new Patient(null,"fatima",new Date(),true,300));
        patientRepository.save(new Patient(null,"khadija",new Date(),true,500));
        List<Patient> patients=patientRepository.findAll();
        patients.forEach(p->{
            System.out.println(p.toString());
        });
        System.out.println("****find Patient By Id****");
        Patient patient=patientRepository.findById(3L).get();
        System.out.println(patient.toString());
        System.out.println("****find Patient By Nom Contains <pat>****");
        List<Patient> patientsC=patientRepository.findByNomContains("pat");
        patientsC.forEach(p->{
            System.out.println(p);
        });
        System.out.println("****Modify Patient****");
        Patient patient1=patientRepository.findById(1L).get();
        System.out.println("****Patient before");
        System.out.println(patient1);
        patient1.setNom("sara");
        System.out.println("****Patient after");
        patientRepository.save(patient1);
        System.out.println(patient1);
        System.out.println("*******Delete a Patient*********");
        patientRepository.deleteById(2L);
        System.out.println("****Patients list after deleting patient with id=2");
        List<Patient> patientsUpdate=patientRepository.findAll();
        patientsUpdate.forEach(p->{
            System.out.println(p.toString());
        });

    }
}
