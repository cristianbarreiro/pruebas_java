package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.model.Book;
import org.learnquest.Laptop;

@SpringBootApplication
public class UseOfAnnotationApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(UseOfAnnotationApplication.class, args);
	
		Book book = context.getBean(Book.class);
		
		book.setTitle("Tell Me Your Dreams"); 
		System.out.println("The title of the book is " + book.getTitle());
		
		Laptop comp = context.getBean(Laptop.class);
		comp.setBrand("Dell");
		System.out.println("The brand of laptop is: " + comp.getBrand());
		
	}

}
