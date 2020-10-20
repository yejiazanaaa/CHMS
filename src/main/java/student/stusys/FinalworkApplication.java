package student.stusys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class FinalworkApplication {

	public static void main(String[] args) {
		//SpringApplication.run(StusysApplication.class, args);
		ApplicationContext applicationContext = SpringApplication.run(FinalworkApplication.class, args);
		SpringUtil.setApplicationContext(applicationContext);
		//SpringUtil.printBean();
	}

}
