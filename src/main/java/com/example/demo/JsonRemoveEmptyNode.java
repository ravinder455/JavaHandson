package com.example.demo;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonRemoveEmptyNode {

	public static void main(String[] args) throws IOException {


		String input = "[{\"id\":1,\"name\":{\"first\":\"Yong\",\"last\":\"Mook Kim\"},\"contact\":[{\"type\":\"phone/home\",\"ref\":\"111-111-1234\"},{}]},{\"id\":2,\"name\":{\"first\":\"Yong\",\"last\":\"Zi Lap\"},\"contact\":[{\"type\":\"phone/home\",\"ref\":\"333-333-1234\"},{\"type\":\"phone/work\",\"ref\":\"444-444-4444\"}],\"contactss\":[]}]";
		
	       JsonFactory factory = new JsonFactory();
	       ObjectMapper mapper = new ObjectMapper(factory);
	       Object rootNode = mapper.readTree(input);  

	       Object result = null;
	       if(rootNode instanceof ObjectNode) {
	    	   result = JSONUtils.removeEmptyFields((ObjectNode) rootNode);
	       } else if(rootNode instanceof ArrayNode) {
	    	   result = JSONUtils.removeEmptyFields((ArrayNode) rootNode);
	       }
	       
	       System.out.println(input);
	       System.out.println(mapper.writeValueAsString(result));
	}

	
	
}
