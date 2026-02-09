package framedesign.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	//method to scan Json file and convert to HashMap
	public List<HashMap<String, String>> convertJsonDataToMap() throws IOException {
		//read Json to String by using FileUtils class
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\framedesign\\data\\purchaseOrder.json"), StandardCharsets.UTF_8);
		
		//String to Map, import Jackson Databind Library dependency
		//using ObjectMapper class for converting between Java Objects and JSON data
		
		ObjectMapper mapper = new ObjectMapper();
		//readValue from String content to HashMap with TypeReference <...>(){}
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;//{{map},{map1}}
		
		
	}

}
