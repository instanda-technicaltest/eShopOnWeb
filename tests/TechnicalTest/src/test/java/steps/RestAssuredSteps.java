package steps;


import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import com.fasterxml.jackson.databind.ObjectMapper;

import dataTypes.CatalogItem;
import dataTypes.CatalogItemResponse;
import helper.Data;
import helper.Reporter;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class RestAssuredSteps {
	Data data = new Data();
	
	RequestSpecification httpRequest;
	Response response;
	
	@When ("user builds API request")
	public void buildAPIRequest() {

		RequestSpecBuilder request_builder = new RequestSpecBuilder();

		request_builder.setBaseUri(data.getData("BaseURI"));      
		request_builder.setBasePath(data.getData("BasePath"));
		Reporter.report("Pass","Build Request with endpoint as "+ "<b>"+data.getData("BaseURI")+data.getData("BasePath") +"</b>", "Request build with endpoint as "+ "<b>"+data.getData("BaseURI")+data.getData("BasePath") +"</b>");	


		if(data.getData("RESTMethod").equals("POST")) {

			request_builder.setContentType(ContentType.JSON);
			Reporter.report("Pass","Build Request with content type as "+ "<b>"+"JSON" +"</b>", "Request build with content type as "+ "<b>"+"JSON" +"</b>");	
			
			request_builder.setBody(data.getData("ReqBody"));
			Reporter.report("Pass","Build Request with Request Body as "+ "<b>"+data.getData("ReqBody") +"</b>", "Request build with Request Body as "+ "<b>"+ data.getData("ReqBody") +"</b>");	
		}

		httpRequest = request_builder.build();
	}
	
	@Then ("user send get request")
	public void getCatalogBrands()  {

		String baseURI = data.getData("BaseURI");      
		String basePath =data.getData("BasePath");
		
        httpRequest = RestAssured.given()
            .relaxedHTTPSValidation();
        try {
	        response = httpRequest.get(baseURI+basePath);
	        
	        
	    	if(!response.equals( null))
				Reporter.report("Pass","User should be able to send get request", "User was able to send get request with endpoint as <b>"+baseURI+basePath+"</b>");
			else
				Reporter.report("Fail","User should be able to send get request", "User could not send get request with endpoint as <b>"+baseURI+basePath+"</b>");
        }catch(Exception e) {
		Reporter.report("Fail","User should be able to send get request", "User could not send get request with endpoint as <b>"+baseURI+basePath+"</b> due to "+ e);
        }
    }
	

	@And ("user send request")	
	public void sendRequest() {


		if(data.getData("RESTMethod").toUpperCase().equals("GET"))
		{
			response = RestAssured.given(httpRequest).relaxedHTTPSValidation().get();
			Reporter.report("Pass","<b>GET</b> Request should be sent", "<b>GET</b> Request was send successfully");	
		}

		else if(data.getData("RESTMethod").toUpperCase().equals("POST"))
		{
			response = RestAssured.given(httpRequest).relaxedHTTPSValidation().post();
			Reporter.report("Pass","<b>POST</b> Request should be sent", "<b>POST</b> Request was send successfully");	
		}
	}
	
	@Then ("status coded should be asserted")
	public void assertStatusCode() throws JsonMappingException, JsonProcessingException {
		
		if((response.getStatusCode()+"").equals( data.getData("StatusCode")))
			Reporter.report("Pass","Status Code should be <b>"+data.getData("StatusCode"), "Status Code successfully asserted as <b>"+response.getStatusCode()+"</b>");
		else
			Reporter.report("Fail","Status Code should be <b>"+data.getData("StatusCode"), "Status Code was <b>"+response.getStatusCode()+"</b>");		
	}
	
	@And ("user should get catalog item as response")
	public CatalogItem validateCatalogItemsInResponse() {

		String JSONString = response.asString();
		ObjectMapper objectMapper = new ObjectMapper();

		CatalogItemResponse brands=null;
		CatalogItem item = null;
		try {
			brands = objectMapper.readValue(JSONString, CatalogItemResponse.class);
			item = brands.getCatalogItem();
			System.out.println("Brand Name is "+brands.getCatalogItem().getName());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		if(!brands.getCatalogItem().getName().equals( null))
			Reporter.report("Pass","Catalog Item details should be fetched", "Catalog Item details of Item: <b>"+brands.getCatalogItem().getName()+"</b> was fetched");
		else
			Reporter.report("Fail","Catalog Item details should be fetched", "Catalog Item details of Item could not be fetched");	
		
		return item;
	}



	@Then ("Brand name should be asserted")
	public void assertBrandNameInResponse() throws JsonMappingException, JsonProcessingException {
		
		JsonPath jsonPathObj = response.jsonPath();
		String FirstBrandName = jsonPathObj.get("catalogBrands[0].name");
		
		System.out.println(FirstBrandName);
		
		List<String> Brands = jsonPathObj.get("catalogBrands.name");
		System.out.println("Number of brands are"+Brands.size());
	    
		if(Brands.get(0).equals( "Azure"))
			Reporter.report("Pass","First Brand Name should be <b>Azure</b>", "First Brand Name was <b>Azure</b>");
		else
			Reporter.report("Fail","First Brand Name should be <b>Azure</b>", "First Brand Name was not <b>Azure</b>");
			
	}
	
	@Then ("user authenticate should be sucessfull")
	public void validatesucessfullAuthentication() {
		
		JsonPath jsonPathObj = response.jsonPath();
		boolean result = jsonPathObj.get("result");
			    
		if(result)
			Reporter.report("Pass","Authenticate should be sucessfull", "Authenticate was sucessfull");
		else
			Reporter.report("Fail","Authenticate should be sucessfull", "Authenticate was not sucessfull");
		
		String bearerToken = jsonPathObj.get("token");
	    
		if(!bearerToken.equals(""))
			Reporter.report("Pass","Bearer token should be create", "Bearer token was created");
		else
			Reporter.report("Fail","Bearer token should be create", "Bearer token was not created");
	}
	
	@Then ("user authenticate should not be sucessfull")
public void validateUnsucessfullAuthentication() {
		
		JsonPath jsonPathObj = response.jsonPath();
		boolean result = jsonPathObj.get("result");
			    
		if(result)
			Reporter.report("Fail","Authenticate should not be sucessfull", "Authenticate was sucessfull");
		else
			Reporter.report("Pass","Authenticate should not be sucessfull", "Authenticate was not sucessfull");
			
		
		String bearerToken = jsonPathObj.get("token");
	    
		if(!bearerToken.equals(""))
			Reporter.report("Fail","Bearer token should not be create", "Bearer token was created");
		else
			Reporter.report("Pass","Bearer token should not be create", "Bearer token was not created");
	}
	
	public static void main(String[] args) {



	
    }}

