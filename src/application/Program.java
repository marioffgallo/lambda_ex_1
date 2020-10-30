package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employees;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter full file path: ");
		String path = sc.nextLine();
		System.out.print("Enter the salary: ");
		Double salaryInput = sc.nextDouble();
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			
			List<Employees> list = new ArrayList<>();
			
			String line = br.readLine();
			
			while(line != null) {
				
				String[] listSplit = line.split(",");
				
				list.add(new Employees(listSplit[0], listSplit[1], Double.parseDouble(listSplit[2])));
				line = br.readLine();
			}
			
			System.out.println("Email of people whose salary is more than " + salaryInput + " :");
			
			List<String> listEmail = list.stream()
					.filter(p -> p.getSalary() >= salaryInput)
					.map(p -> p.getEmail()).sorted((s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase()))
					.collect(Collectors.toList());
			
			listEmail.forEach(System.out::println);
			
			Double sum = list.stream()
					.filter(p -> p.getName().charAt(0) == 'M')
					.map(p -> p.getSalary())
					.reduce(0.0, (x,y) -> x + y);
			
			System.out.print("Sum of salary of people whose name starts with 'M': " + sum);
		}
		catch (IOException e) {
			System.out.print("Error: " + e.getMessage());
		}
		
		sc.close();
	}

}
