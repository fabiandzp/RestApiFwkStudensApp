package com.studentapp.junit.studentsIdInfo;

import java.util.ArrayList;
import java.util.HashMap;

import org.hamcrest.collection.IsMapContaining;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.studentapp.cucumber.serenity.StudentSerenitySteps;
import com.studentapp.module.StudentClass;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.ReusableSpecifications;
import com.studentapp.utils.TestUtils;

import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;

import static org.hamcrest.Matcher.*;
import static org.junit.Assert.*;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentCRUDTest extends TestBase {

	static String firstName = "Fabian" + TestUtils.getRandomValue();
	static String lastName = "Zabala" + TestUtils.getRandomValue();
	static String programme = "ComputerScience";
	static String email = "fabiandzp" + TestUtils.getRandomValue() + "@live.com";
	static int studentId;

	@Steps
	StudentSerenitySteps steps;

	@Title("Create a new student")
	@Test
	public void test001() {
		ArrayList<String> courses = new ArrayList<String>();
		courses.add("JAVA");
		courses.add("C++");

		steps.createStudent(firstName, lastName, email, programme, courses).statusCode(201)
				.spec(ReusableSpecifications.getGenericResponseSpec());
	}

	@Title("Verify if Student was added to the application")
	@Test
	public void test002() {

		HashMap<String, Object> value = steps.getStudentInfoByFirstName(firstName);
		assertThat(value, IsMapContaining.hasValue(firstName));

		System.out.println("The value is : " + value);

		studentId = (int) value.get("id");

	}

	@Title("Update the user information and verify updated information")
	@Test
	public void test003() {

		ArrayList<String> courses = new ArrayList<String>();
		courses.add("JAVA");
		courses.add("C++");

		firstName = firstName + "_Updated";
		steps.updateStudent(studentId, firstName, lastName, email, programme, courses);

		// validate if student is there
		HashMap<String, Object> value = steps.getStudentInfoByFirstName(firstName);
		assertThat(value, IsMapContaining.hasValue(firstName));
	}

	@Title("Delete student and verify if student is deleted!")
	@Test
	public void test004() {
		steps.deleteStudent(studentId);
		steps.getStudentById(studentId).statusCode(404);
	}

}
