package com.drpicox.dddapp;

import com.drpicox.queue.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({Queue.class})
public class DddApplication {

	public static void main(String[] args) {
		SpringApplication.run(DddApplication.class, args);
	}
}
