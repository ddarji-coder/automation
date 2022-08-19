package RestTest;

import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import module.TMSandboxPage;
import net.minidev.json.JSONArray;

public class TMSandboxTest {	
	
	TMSandboxPage sandboxPage;
	Response response;
	
	@BeforeClass
	public void presetup() throws Exception {
		sandboxPage = new TMSandboxPage();
		sandboxPage.initializeTMSandbox();
		response = sandboxPage.getTMSandBoxResponse();
	}
    
	@Test
	public void verify_name_in_response() {	
		Assert.assertEquals(sandboxPage.getTmsandBoxValueByKey("Name"), "Carbon credits");
	}
	
	@Test
	public void verify_canrelist_in_response() {
		Assert.assertEquals(sandboxPage.getTmsandBoxValueByKey("CanRelist"), true);
	}
	
	@Test
	public void verify_promotions_detail_in_response() {
		JSONArray promotionsJsonArray = JsonPath.parse(response.getBody().asString()).read("$.Promotions[?(@.Name=='Gallery')]");
		@SuppressWarnings("unchecked")
		Map<String, String> promotionsMap = (Map<String, String>) promotionsJsonArray.get(0);
		Assert.assertTrue(promotionsMap.get("Description").contains("Good position in category"), "Description does not match.");
	}	
}
