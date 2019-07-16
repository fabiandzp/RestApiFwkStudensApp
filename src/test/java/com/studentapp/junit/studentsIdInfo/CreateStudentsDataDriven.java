package com.studentapp.junit.studentsIdInfo;

import java.util.ArrayList;

import org.eclipse.jetty.websocket.api.StatusCode;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.opera.core.systems.scope.protos.EcmascriptProtos.EvalResult.Status;
import com.studentapp.cucumber.serenity.StudentSerenitySteps;
import com.studentapp.testbase.TestBase;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.UseTestDataFrom;

@Concurrent(threads="4x") //Will run 4 test x core
@UseTestDataFrom("testdata/studentinfo.csv")//Annotacion para usar test data
@RunWith(SerenityParameterizedRunner.class) //test know now that is a data driven test and will be executed, depend of number of rows in csv file
public class CreateStudentsDataDriven extends TestBase{
	
	private String firstName;
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProgramme() {
		return programme;
	}

	public void setProgramme(String programme) {
		this.programme = programme;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public StudentSerenitySteps getSteps() {
		return steps;
	}

	public void setSteps(StudentSerenitySteps steps) {
		this.steps = steps;
	}

	private String lastName;
	private String email;
	private String programme;
	private String course;
	
	
	@Steps
	StudentSerenitySteps steps;
	
	@Title("Data Driven Test for adding multiple to the student App.")
	@Test
	public void createMultipleStudents() {
		ArrayList<String> courses = new ArrayList<>();
		courses.add(course);
		
		steps.createStudent(firstName, lastName, email, programme, courses)
		.statusCode(201);
		
	}
		
}
