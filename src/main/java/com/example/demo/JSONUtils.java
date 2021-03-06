package com.example.demo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JSONUtils {

	
	public static String sanitizeJson(String jsonString) {
		
		JsonFactory factory = new JsonFactory();
	    ObjectMapper mapper = new ObjectMapper(factory);
		
	    String prevRes = "";
	    String latestRes = jsonString.trim();
	    
	    while(!prevRes.equals(latestRes)) {
	    	Object rootNode;
			try {
				Object result = null;
				rootNode = mapper.readTree(latestRes);
				
				if(rootNode instanceof ObjectNode) {
				   result = JSONUtils.removeEmptyFields((ObjectNode) rootNode);
				   if(null == result) result = "{}";
				} else if(rootNode instanceof ArrayNode) {
				   result = JSONUtils.removeEmptyFields((ArrayNode) rootNode);
				   if(null == result) result = "[]";
				}
				
				prevRes = latestRes;
				latestRes = result.toString();
				System.out.println("==>" + latestRes);
			} catch (IOException e) {
				System.out.println("Error in cleaning empty json objects/elements");
				prevRes = jsonString.trim();
				latestRes = jsonString.trim();
			}
	    }
		return latestRes;
	}
	
	private static ObjectNode removeEmptyFields(final ObjectNode jsonNode) {
        ObjectNode ret = new ObjectMapper().createObjectNode();
        Iterator<Entry<String, JsonNode>> iter = jsonNode.fields();

        if(!iter.hasNext()) return null;
        while (iter.hasNext()) {
            Entry<String, JsonNode> entry = iter.next();
            String key = entry.getKey();
            JsonNode value = entry.getValue();

            if (value instanceof ObjectNode) {
            	ObjectNode res = removeEmptyFields((ObjectNode) value);
            	if(res != null) {
            		Map<String, ObjectNode> map = new HashMap<String, ObjectNode>();
                    map.put(key, res);
                    ret.setAll(map);
            	}
            }
            else if (value instanceof ArrayNode) {
            	ArrayNode res = removeEmptyFields((ArrayNode) value);
                if(res != null) ret.set(key, res);
            }
            else if (value.asText() != null && !value.asText().isEmpty()) {
                ret.set(key, value);
            }
        }

        return ret;
    }


    /**
     * Removes empty fields from the given JSON array node.
     * @param an array node
     * @return the array node with empty fields removed
     */
    private static ArrayNode removeEmptyFields(ArrayNode array) {
        ArrayNode ret = new ObjectMapper().createArrayNode();
        Iterator<JsonNode> iter = array.elements();

        if(!iter.hasNext()) return null;
        
        while (iter.hasNext()) {
            JsonNode value = iter.next();

            if (value instanceof ArrayNode) {
            	ArrayNode res = removeEmptyFields((ArrayNode)(value));
            	if(res != null) ret.add(res);
            }
            else if (value instanceof ObjectNode) {
            	ObjectNode res = removeEmptyFields((ObjectNode)(value));
            	if(res != null) ret.add(res);
            }
            else if (value != null && !value.textValue().isEmpty()){
                ret.add(value);
            }
        }

        return ret;
    }
}
