package com.learning.serverapp;

import com.learning.serverapp.enumeration.Status;
import com.learning.serverapp.model.Server;
import com.learning.serverapp.repo.ServerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

public class ServerappApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerappApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ServerRepo serverRepo){
		return args -> {
			serverRepo.save(new Server(
					null,
					"192.168.1.160",
					"Ubuntu Linux",
					"16 GB",
					"Personal PC",
					"http://localhost:8080/server/image/server1.png",
					Status.SERVER_UP
			));
			serverRepo.save(new Server(
					null,
					"192.168.1.58",
					"Fedora Linux",
					"16 GB",
					"Dell Tower",
					"http://localhost:8080/server/image/server3.png",
					Status.SERVER_DOWN
			));
			serverRepo.save(new Server(
					null,
					"192.168.1.43",
					"Debian Server",
					"16 GB",
					"Web Server",
					"http://localhost:8080/server/image/server2.png",
					Status.SERVER_UP
			));
			serverRepo.save(new Server(
					null,
					"192.168.1.26",
					"Amazon EC2 Server",
					"16 GB",
					"Cloud Server",
					"http://localhost:8080/server/image/server4.png",
					Status.SERVER_UP
			));
		};
	}
}
