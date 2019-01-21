package com.example.demo;

import java.io.IOException;

public class JsonRemoveEmptyNode {

	public static void main(String[] args) throws IOException {


		String input = "[{\"id\":1,\"name\":{\"first\":\"Yong\",\"last\":\"Mook Kim\"},"
				+ "\"contact\":[{\"type\":\"phone/home\",\"ref\":\"111-111-1234\"},{}]},"
				+ "{\"id\":2,\"name\":{\"first\":\"Yong\",\"last\":\"Zi Lap\"},"
				+ "\"contact\":[{\"type\":\"phone/home\",\"ref\":\"333-333-1234\"},"
				+ "{\"type\":\"phone/work\",\"ref\":\"444-444-4444\"}],"
				+ "\"contactss\":[], \"contactzz\":{\"A\":{}}}]";
		
		    
       System.out.println(input);
       System.out.println(JSONUtils.sanitizeJson(input));
		      
	       
	}

	
	
}
