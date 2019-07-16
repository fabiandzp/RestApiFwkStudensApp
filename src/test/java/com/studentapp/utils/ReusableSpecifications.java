package com.studentapp.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.*;

import java.util.concurrent.TimeUnit;

public class ReusableSpecifications {

	
	public static RequestSpecBuilder rspec; 
	public static RequestSpecification requestSpecification;
	
	public static ResponseSpecBuilder respec; 
	public static ResponseSpecification ResponseSpecification;
	
	
	
	public static RequestSpecification getGenericRequestSpec() {
		
		rspec = new RequestSpecBuilder();
		rspec.setContentType(ContentType.JSON); // valore que se envia como contenido para put post and path
		requestSpecification = rspec.build();   // Esto nos da el requestSpecification object
		
		//la ide de usar request specification podemos crear multiples header values, cookies estos se pueden setear aqui esa es la intencion de crear un request reusable
		
		return requestSpecification;
		
	}
	
	//metodo que nos retorna un reponse generico
	
	public static ResponseSpecification getGenericResponseSpec() {
		
		respec = new ResponseSpecBuilder();
		
		//When response comes back make sure that headers content type has app json 
		respec.expectHeader("Content-Type", "application/json;charset=UTF-8");
		respec.expectHeader("Transfer-Encoding", "chunked");
		respec.expectResponseTime(lessThan(10L), TimeUnit.SECONDS);
		ResponseSpecification = respec.build();
		
		return ResponseSpecification;
		
	}
	
}
