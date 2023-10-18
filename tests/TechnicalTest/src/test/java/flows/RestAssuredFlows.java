package flows;


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


public class RestAssuredFlows {
	Data data = new Data();
	
	RequestSpecification httpRequest;
	public static Response response;
	
	
	public void buildAPIRequest(String basePath) {

		RequestSpecBuilder request_builder = new RequestSpecBuilder();
	
		request_builder.setBaseUri(data.getData("BaseURI"));      
		request_builder.setBasePath(basePath);
		Reporter.report("Pass","Build Request with endpoint as "+ "<b>"+data.getData("BaseURI")+basePath +"</b>", "Request build with endpoint as "+ "<b>"+data.getData("BaseURI")+basePath +"</b>");	

		httpRequest = request_builder.build();
	}
	
	public void buildAPIRequest(String basePath, String reqBody, String token) {

		RequestSpecBuilder request_builder = new RequestSpecBuilder();
	
		request_builder.setBaseUri(data.getData("BaseURI"));      
		request_builder.setBasePath(basePath);
		Reporter.report("Pass","Build Request with endpoint as "+ "<b>"+data.getData("BaseURI")+basePath +"</b>", "Request build with endpoint as "+ "<b>"+data.getData("BaseURI")+basePath +"</b>");	

			request_builder.setContentType(ContentType.JSON);
			Reporter.report("Pass","Build Request with content type as "+ "<b>"+"JSON" +"</b>", "Request build with content type as "+ "<b>"+"JSON" +"</b>");	
			request_builder.addHeader("Authorization", "Bearer "+token);
			request_builder.setBody(reqBody);
			Reporter.report("Pass","Build Request with Request Body as "+ "<b>"+reqBody +"</b>", "Request build with Request Body as "+ "<b>"+ reqBody +"</b>");	
		

		httpRequest = request_builder.build();
	}

	public void buildAPIRequest(String basePath, String reqBody) {

		RequestSpecBuilder request_builder = new RequestSpecBuilder();
	
		request_builder.setBaseUri(data.getData("BaseURI"));      
		request_builder.setBasePath(basePath);
		Reporter.report("Pass","Build Request with endpoint as "+ "<b>"+data.getData("BaseURI")+basePath +"</b>", "Request build with endpoint as "+ "<b>"+data.getData("BaseURI")+basePath +"</b>");	

			request_builder.setContentType(ContentType.JSON);
			Reporter.report("Pass","Build Request with content type as "+ "<b>"+"JSON" +"</b>", "Request build with content type as "+ "<b>"+"JSON" +"</b>");	
			
			request_builder.setBody(reqBody);
			Reporter.report("Pass","Build Request with Request Body as "+ "<b>"+reqBody +"</b>", "Request build with Request Body as "+ "<b>"+ reqBody +"</b>");	
		

		httpRequest = request_builder.build();
	}


	public void sendRequest(String requestType) {

		if(requestType.toUpperCase().equals("GET"))
		{
			response = RestAssured.given(httpRequest).relaxedHTTPSValidation().get();
			Reporter.report("Pass","<b>GET</b> Request should be sent", "<b>GET</b> Request was send successfully");	
		}

		else if(requestType.toUpperCase().equals("POST"))
		{
			response = RestAssured.given(httpRequest).relaxedHTTPSValidation().post();
			Reporter.report("Pass","<b>POST</b> Request should be sent", "<b>POST</b> Request was send successfully");	
		}
		
	}
	
	public void sendRequest(String requestType, String token) {

		if(requestType.toUpperCase().equals("GET"))
		{
			response = RestAssured.given(httpRequest).relaxedHTTPSValidation().get();
			Reporter.report("Pass","<b>GET</b> Request should be sent", "<b>GET</b> Request was send successfully");	
		}

		else if(requestType.toUpperCase().equals("POST"))
		{
			response = RestAssured.given(httpRequest).relaxedHTTPSValidation().post();
			Reporter.report("Pass","<b>POST</b> Request should be sent", "<b>POST</b> Request was send successfully");	
		}
		else if(requestType.toUpperCase().equals("PUT"))
		{
			response = RestAssured.given(httpRequest).relaxedHTTPSValidation().put();
			Reporter.report("Pass","<b>PUT</b> Request should be sent", "<b>PUT</b> Request was send successfully");	
		}
		else if(requestType.toUpperCase().equals("DELETE"))
		{
			response = RestAssured.given(httpRequest).header("Authorization", "Bearer "+token).relaxedHTTPSValidation().delete();
			Reporter.report("Pass","<b>POST</b> Request should be sent", "<b>POST</b> Request was send successfully");	
		}
	}
	
	public void assertStatusCode()  {
		
		if((response.getStatusCode()+"").equals( data.getData("StatusCode")))
			Reporter.report("Pass","Status Code should be <b>"+data.getData("StatusCode"), "Status Code successfully asserted as <b>"+response.getStatusCode()+"</b>");
		else
			Reporter.report("Fail","Status Code should be <b>"+data.getData("StatusCode"), "Status Code was <b>"+response.getStatusCode()+"</b>");		
	}
	
	public void assertStatusCode(int code)  {
		
		if((response.getStatusCode()+"").equals( code+""))
			Reporter.report("Pass","Status Code should be <b>"+code, "Status Code successfully asserted as <b>"+response.getStatusCode()+"</b>");
		else
			Reporter.report("Fail","Status Code should be <b>"+code, "Status Code was <b>"+response.getStatusCode()+"</b>");		
	}
	

	public CatalogItem validateCatalogItemsInResponse() {

		String JSONString = response.asString();
		System.out.println("JSON"+JSONString);
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

	public String getBrandName(int index)  {
		
		JsonPath jsonPathObj = response.jsonPath();
		String brandName = jsonPathObj.get("catalogBrands["+index+"].name");
		System.out.println(brandName);
		if(!brandName.equals(null))
			Reporter.report("Pass","Brand name with id "+index+" should be present", "Brand Name was present and is <b>"+brandName+"</b>");
		else
			Reporter.report("Fail","Brand name with id "+index+" should be present", "Brand name could not be retrived");
		return brandName;
			
	}
	
public String getTypeName(int index)  {
		
		JsonPath jsonPathObj = response.jsonPath();
		String typeName = jsonPathObj.get("catalogTypes["+index+"].name");
		System.out.println(typeName);
		if(!typeName.equals(null))
			Reporter.report("Pass","Type name with id "+index+" should be present", "Type Name was present and is <b>"+typeName+"</b>");
		else
			Reporter.report("Fail","Type Name with id "+index+" should be present", "Type name could not be retrived");
		return typeName;			
	}


	public void assertBrandName() throws JsonMappingException, JsonProcessingException {
		
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

public String getBearerToken() {
		
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
		
		return bearerToken;
	}
	
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

public void validateDeletedStatus() {
	JsonPath jsonPathObj = response.jsonPath();
	System.out.println("jsin"+jsonPathObj.toString());
	String status = jsonPathObj.get("status");
	
	try {
	if(status.equals("Deleted"))
		Reporter.report("Pass","Record should be deleted", "Record deleted sucessfully and Status in response is Deleted");
	else
		Reporter.report("Fail","Record should be deleted", "Record not deleted");
	}catch(Exception e)	{
		Reporter.report("Fail","Record should be deleted", "Record not deleted");
	}
}
}

