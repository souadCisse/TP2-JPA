package ma.enset.jpa;

import ma.enset.jpa.entities.Role;
import ma.enset.jpa.entities.User;
import ma.enset.jpa.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class JpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

	@Bean
	CommandLineRunner start(UserService userService){
		return args -> {

			User u=new User();
			u.setUserName("sara");
			u.setPassword("12345");
			userService.addNewUser(u);

			User u1=new User();
			u1.setUserName("fati");
			u1.setPassword("123ddll");
			userService.addNewUser(u1);

			Stream.of("STUDENT","ADMIN","USER").forEach(r->{
				Role role=new Role();
				role.setRoleName(r);
				userService.addNewRole(role);
			});

			userService.addRoleToUser("sara","STUDENT");
			userService.addRoleToUser("sara","ADMIN");
			userService.addRoleToUser("fati","STUDENT");
			userService.addRoleToUser("fati","USER");

			try{
				User user=userService.authenticate("sara","12345");
				System.out.println(user.getUserId());
				System.out.println(user.getUserName());
				System.out.println("Roles=>");
				user.getRoles().forEach(role -> {
					System.out.println("Role=>"+role.toString());
				});

			}catch (Exception exception){
				exception.printStackTrace();

			}
		};
	}

}
