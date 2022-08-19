package module;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import util.APIConstant;

public class TMSandboxPage {
	
	JsonPath json;
	
	/**
	 * Initialize base URI of tmsandbox.
	 */
	public void initializeTMSandbox() {
		System.out.println("API Initialisation.");
		RestAssured.baseURI = APIConstant.API_TMSANDBOX;
	}
	
	/**
	 * This method will do GET API call to fetch tmsandbox data.
	 * @return
	 * @throws Exception
	 */
	public Response getTMSandBoxResponse() throws Exception {
		RequestSpecification request = RestAssured.given();
		Response response = request.get(APIConstant.API_TMSANDBOX_URI);
		if (response == null) {
			throw new Exception("MSandBoxResponse is Empty.");    
		}
		// set json path of response for future use.
		json = response.jsonPath();
		return response;
	}
	
	/**
	 * Get value from json object by key name.
	 * @param key
	 * @return
	 */
	public Object getTmsandBoxValueByKey(String key) {
		return json.get(key);
	}
	
}
