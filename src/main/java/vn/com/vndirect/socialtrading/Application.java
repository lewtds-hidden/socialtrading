package vn.com.vndirect.socialtrading;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication

@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        //FollowerDao followerDao = new FollowerDao(new MyDataSource());
       // System.out.println(followerDao.findAll().size());
    }
	 
	/*@Bean
	public InMemory memory() {
		return new InMemory();
	}*/
}