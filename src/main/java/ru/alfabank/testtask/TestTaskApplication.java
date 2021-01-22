package ru.alfabank.testtask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.alfabank.testtask.service.ApplicationContextHolder;
import ru.alfabank.testtask.service.ParserService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;


@SpringBootApplication
public class TestTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestTaskApplication.class, args);
        ParserService parserService = ApplicationContextHolder
                .getApplicationContext()
                .getBean("ParserService", ParserService.class);

        String[] params = args[0].split(":", 2);
		try {
			switch (params[0]) {
				case "file":
					parserService.parseXml(new FileInputStream(new File(params[1])));
					break;
				case "classpath":
					parserService.parseXml(TestTaskApplication.class.getClassLoader().getResourceAsStream(params[1]));
					break;
				case "url":
					parserService.parseXml(new URL(params[1]).openStream());
					break;
				default:
					parserService.parseXml(new FileInputStream(new File("example.xml")));
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


