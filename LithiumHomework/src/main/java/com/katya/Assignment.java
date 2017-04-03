package main.java.com.katya;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Assignment {
	
	public static void main(String[] args) {
		String testDataPath = "./src/main/java/com/katya/inputData.json";
		JSONObject inputJson = Assignment.readJsonFromFile(testDataPath);
		String result = Assignment.findDayWithLargestTSpread(inputJson);
		System.out.println(result);
	}
	
	public static JSONObject readJsonFromFile(String path) {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
        try {
            Object obj = parser.parse(new FileReader(path));
            jsonObject = (JSONObject) obj;       
        }         
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
        catch (IOException e) {
            e.printStackTrace();
        } 
        catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;        
	}

	public static String findDayWithLargestTSpread(JSONObject json) {
		double largestTempSpread = 0.0;
		long dayOfLargestSpread = 0;
		JSONArray days = (JSONArray) json.get("days");
		
		for (int i=0; i<days.size(); i++) {
			JSONObject dayObj = (JSONObject) days.get(i);
			double high = ((Number)dayObj.get("high")).doubleValue();
			double low = ((Number) dayObj.get("low")).doubleValue();
			double tempInterval = high - low;
			long theDay = (long) dayObj.get("day");
			
			if (largestTempSpread < tempInterval) {
				largestTempSpread = tempInterval;
				dayOfLargestSpread = theDay;
			}
		}
		return (dayOfLargestSpread + " " + json.get("month") + ", " + json.get("year"));
	}
	
}
