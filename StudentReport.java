package com.training.jdbc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentReport {

	public List<Student> generateStudentReport(String filePath) throws IOException {

		List<Student> list = new ArrayList<>();
		FileReader fr = new FileReader(new File(filePath));
		BufferedReader br = new BufferedReader(fr);
		String l;
		int[] score = new int[3];
		while ((l = br.readLine()) != null) {
			String[] a = l.split(",");
			int regno = Integer.parseInt(a[0]);
			//System.out.println("file data" + regno);
			Student s = new Student();
			try {
				if (validate(regno)) {

					score[0] = Integer.parseInt(a[2]);
					score[1] = Integer.parseInt(a[3]);
					score[2] = Integer.parseInt(a[4]);

					s.setRegno(regno);
					s.setName(a[1]);
					s.setWebScore(score[0]);
					s.setSqlScore(score[1]);
					s.setJavaScore(score[2]);
					s.calculateAverageScore(score);
					list.add(s);
				}
			}

			catch (InvalidNumberException e) {
				System.out.println(e.getMessage());
			}
		}
		br.close();
		return list;

	}
//find the student with maximum average
	public List<Student> findMaximumAverage()
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		List<Student> studentList=new ArrayList<>();
	  try
	  {
		con = DBHandler.getConnection();
		String query = "select * from student where average=(select max(average) from student)";
		ps = con.prepareStatement(query);
		rs=ps.executeQuery();
		while(rs.next())
		{
			Student s=new Student();
			s.setRegno(rs.getInt(1));
			s.setName(rs.getString(2));
			s.setWebScore(rs.getInt(3));
			s.setSqlScore(rs.getInt(4));
			s.setJavaScore(rs.getInt(5));
			s.setAverageScore(rs.getDouble(6));
			studentList.add(s);
		}
	  }
	  catch(SQLException e)
	  {
		  System.out.println(e.getMessage());
	  }
	  catch(ClassNotFoundException e)
	  {
		  System.out.println(e.getMessage());
	  }
	  return studentList;
		
	}
	public boolean validate(int regno) throws InvalidNumberException {
		if (regno > 0) {
			return true;
		} else {
			throw new InvalidNumberException("Reg number should be a positive value");
		}
	}
	public void saveStudentData(List<Student> list) {
		// jdbc code to insert
		Connection con = null;
		PreparedStatement ps = null;
		int rows=0;
	  try
	  {
		con = DBHandler.getConnection();
		String query = "insert into student(regno,sname,subject1,subject2,subject3,average) values(?,?,?,?,?,?)";
		ps = con.prepareStatement(query);

		for (Student s : list) {
			ps.setInt(1, s.getRegno());
			ps.setString(2, s.getName());
			ps.setInt(3, s.getWebScore());
			ps.setInt(4, s.getSqlScore());
			ps.setInt(5, s.getJavaScore());
			ps.setDouble(6, s.getAverageScore());
			rows = ps.executeUpdate();
			if(rows>0)
				System.out.println("Inserted successfully");
		}
	
	  }
	  catch(SQLException e)
	  {
		  System.out.println(e.getMessage());
	  }
	  catch(ClassNotFoundException e)
	  {
		  System.out.println(e.getMessage());
	  }
	}
}
