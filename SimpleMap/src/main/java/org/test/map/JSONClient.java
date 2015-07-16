package org.test.map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONClient {

    
    public static String generateAddOrderMessage(Configuration order) {
        
    	JSONObject orderJSON = new JSONObject();      
        orderJSON.put("Model", order.getModel());
        orderJSON.put("Engine", order.getEngine());
        orderJSON.put("Color", order.getColor());
        orderJSON.put("Interior", order.getInterior());
        orderJSON.put("Exterior", order.getExterior());
        
        JSONObject rootJSON = new JSONObject();
        rootJSON.put("Configuration", orderJSON);
        return rootJSON.toJSONString();
    }
    
    public static String generateUpdateOrderMessage(Configuration order, String id) {
    
		JSONObject updateJSON = new JSONObject(); 
	    updateJSON.put("ID", id);
	
    	JSONObject configJSON = new JSONObject();      
        configJSON.put("Model", order.getModel());
        configJSON.put("Engine", order.getEngine());
        configJSON.put("Color", order.getColor());
        configJSON.put("Interior", order.getInterior());
        configJSON.put("Exterior", order.getExterior());
        updateJSON.put("Configuration", configJSON);
       
        JSONObject rootJSON = new JSONObject();
        rootJSON.put("update", updateJSON);
        return rootJSON.toJSONString();
    }
    
    public static String generateDeleteOrderMessage(String id) {
        
    	JSONObject orderJSON = new JSONObject();      
        orderJSON.put("ID", id);
        JSONObject rootJSON = new JSONObject();
        rootJSON.put("delete", orderJSON);
        return rootJSON.toJSONString();
    }
    
    public static String generateBuyOrderMessage(String model, String qty) {
        
    	JSONObject orderJSON = new JSONObject();      
        orderJSON.put("Model", model);
        orderJSON.put("Quantity", qty);
        
        JSONObject rootJSON = new JSONObject();
        rootJSON.put("Configuration", orderJSON);
        return rootJSON.toJSONString();
    }

    
    
    public static Configuration getConfiguration(String orderJson){
       
    	if (orderJson != null && !orderJson.isEmpty()) {
	        Configuration order = new Configuration();
	        
	        try {
	        	JSONParser parser = new JSONParser();
	            Object obj = parser.parse(orderJson);
	            
	            JSONObject jsonObject = (JSONObject)obj;
	            JSONObject configObject = (JSONObject) jsonObject.get("Configuration");
	            if (configObject != null) {
		            order.setModel((String)configObject.get("Model"));
		            order.setEngine((String)configObject.get("Engine"));
		            order.setColor((String)configObject.get("Color"));
		            order.setInterior((String)configObject.get("Interior"));
		            order.setExterior((String)configObject.get("Exterior"));
		            return order;
	            }
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
    	}
    	return null;
    }
    
    
    public static String getPrice(String orderJson){
        //placeOrderRespone":{"getQuotation":{"price"
        try {
        	JSONParser parser = new JSONParser();
            Object obj = parser.parse(orderJson);
            
            JSONObject jsonObject = (JSONObject)obj;
            JSONObject orderObject = (JSONObject) jsonObject.get("placeOrderRespone");
            JSONObject quotationObject = (JSONObject) orderObject.get("getQuotation");
            if (quotationObject != null) {
	            String price = quotationObject.get("price").toString();
	            return price;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    

    /**
     * Populates Token object using folloing JSON String
     * {
     * "token_type": "bearer",
     * "expires_in": 3600000,
     * "refresh_token": "f43de118a489d56c3b3b7ba77a1549e",
     * "access_token": "269becaec9b8b292906b3f9e69b5a9"
     }
     * @param accessTokenJson
     * @return
     */
    public static Token getAccessToken(String accessTokenJson){
        JSONParser parser = new JSONParser();

        Token token = new Token();
        try {
            Object obj = parser.parse(accessTokenJson);
            JSONObject jsonObject = (JSONObject)obj;
            token.setAccessToken((String)jsonObject.get("access_token"));
            long expiresIn = ((Long)jsonObject.get("expires_in")).intValue();
            token.setExpiresIn(expiresIn);
            token.setRefreshToken((String)jsonObject.get("refresh_token"));
            token.setTokenType((String)jsonObject.get("token_type"));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return token;

    }
}





