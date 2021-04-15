package com.training.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {


		List<Student> list = new ArrayList<>();
		StudentReport report = new StudentReport();
		
		// to read data from the file and return as a list
//		list = report.generateStudentReport("student.txt");
//		list.forEach(s->System.out.println(s));
		
		//list data is saved to the table
//		report.saveStudentData(list);
		
		//students with maximum average
		
		List<Student> avgList=report.findMaximumAverage();
		avgList.forEach(s->System.out.println(s));
		
	
	}

}
