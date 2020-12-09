package com.mile1.main;

import com.mile1.bean.Student;
import com.mile1.exception.NullMarksArrayException;
import com.mile1.exception.NullNameException;
import com.mile1.exception.NullStudentException;
import com.mile1.service.StudentReport;
import com.mile1.service.StudentService;

public class StudentMain {
	static Student data[] = new Student[10];
	
	static 
	{
		for (int i = 0; i < data.length; i++) 
			data [i]= new Student(); 
		
		data [0] = new Student("Sekar", new int[] {25, 15, 35});
		data [1] = new Student("Suman", new int[] {55, 60, 40});
		data [2] = null;
		data [3] = new Student("Sara", null);
		data [4] = new Student("Roy", new int[] {90, 92, 80}); 
		data [5] = new Student("Rahul", new int[] {35, 40, 50}); 
		data [6] = new Student("Neha", null); 
		data [7] = null; 
		data [8] = new Student(null, new int[] {25, 29, 28}); 
		data [9] = null;
		
	}
	
	public static void main(String[] args) {
		StudentReport studentReport = new StudentReport();
		StudentService studentService = new StudentService();
		
		System.out.println("Grade Calculation:");
		
		String ob = null;
		
		for (int i = 0; i < data.length; i++) 
		{
			try 
			{
				ob = studentReport.validate(data[i]);
			} 
			catch (NullNameException e)
			{
				ob = "NullNameException occured";
			}
			catch (NullMarksArrayException e) 
			{
				ob = "NullMarksArrayException occured";
			} 
			catch (NullStudentException e)
			{
				ob = "NullStudentException occured";
			}
			
			System.out.println("GRADE = " + ob);
		}
		
		System.out.println("Number of Objects with Marks array as null = " +studentService.findNumberOfNullMarks(data));
		System.out.println("Number of Objects with Name as null = " +studentService.findNumberOfNullNames(data));
		System.out.println("Number of Objects that are entierly null = " +studentService.findNumberOfNullObjects(data));
	}

}