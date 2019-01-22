package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import org.junit.Assert;

@RunWith(MockitoJUnitRunner.class)
public class JSONUtilsTest {
	
	@Test
	public void testRemoveEmptyFieldsForArray() {
		String jsonString = "[{\"id\":1,\"name\":{\"first\":\"Yong\",\"last\":\"Mook Kim\"},"
				+ "\"contact\":[{\"type\":\"phone/home\",\"ref\":\"111-111-1234\"},{}]},"
				+ "{\"id\":2,\"name\":{\"first\":\"Yong\",\"last\":\"Zi Lap\"},"
				+ "\"contact\":[{\"type\":\"phone/home\",\"ref\":\"333-333-1234\"},"
				+ "{\"type\":\"phone/work\",\"ref\":\"444-444-4444\"}],"
				+ "\"contactss\":[], \"contactzz\":{\"A\":{}}}]";
		
		String response = JSONUtils.sanitizeJson(jsonString);
		Assert.assertEquals("[{\"id\":1,\"name\":{\"first\":\"Yong\",\"last\":\"Mook Kim\"},"
				+ "\"contact\":[{\"type\":\"phone/home\",\"ref\":\"111-111-1234\"}]},"
				+ "{\"id\":2,\"name\":{\"first\":\"Yong\",\"last\":\"Zi Lap\"},"
				+ "\"contact\":[{\"type\":\"phone/home\",\"ref\":\"333-333-1234\"},"
				+ "{\"type\":\"phone/work\",\"ref\":\"444-444-4444\"}]}]", response);
	}
	
	@Test
	public void testRemoveEmptyFieldsForObject() {
		String jsonString = "{\"detail\":{\"id\":1,\"name\":{\"first\":\"Yong\",\"last\":\"Mook Kim\"},\"contact\":[{\"type\":\"\",\"ref\":\"111-111-1234\"}]}}";
		String response = JSONUtils.sanitizeJson(jsonString);
		Assert.assertEquals("{\"detail\":{\"id\":1,\"name\":{\"first\":\"Yong\",\"last\":\"Mook Kim\"},\"contact\":[{\"ref\":\"111-111-1234\"}]}}", response);
	}
	
	@Test
	public void testRemoveEmptyFieldsForObjectForNoEmptyObjects() {
		String jsonString = "{\"contact\":{\"id\":1,\"name\":{\"first\":\"Yong\",\"last\":\"Mook Kim\"},\"contact\":[{\"type\":\"phone/home\",\"ref\":\"111-111-1234\"}]}}";
		String response = JSONUtils.sanitizeJson(jsonString);
		Assert.assertEquals(jsonString, response);
	}
	
	@Test
	public void testRemoveEmptyFieldsForEmptyObject() {
		String jsonString = "{}";
		String response = JSONUtils.sanitizeJson(jsonString);
		Assert.assertEquals("{}", response);
	}
	
	@Test
	public void testRemoveEmptyFieldsForObjectWithEmptyObjects() {
		String jsonString = "{\"contact\":{}}";
		String response = JSONUtils.sanitizeJson(jsonString);
		Assert.assertEquals("{}", response);
	}
	
	@Test
	public void testRemoveEmptyFieldsForEmptyArray() {
		String jsonString = "[]";
		String response = JSONUtils.sanitizeJson(jsonString);
		Assert.assertEquals("[]", response);
	}
	
	@Test
	public void testRemoveEmptyFieldsForArrayofEmptyObjects() {
		String jsonString = "[{\"contact\":{}}]";
		String response = JSONUtils.sanitizeJson(jsonString);
		Assert.assertEquals("[]", response);
	}

}
