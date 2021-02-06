import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import cucumber.api.DataTable;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GitHubStepDefinitions {
	User user;
	
	@When("^I query Github for username \"([^\"]*)\"$")
	public void i_query_Github_for_username(String name) throws Throwable {
		
		HttpUriRequest request = new HttpGet("https://api.github.com/users/" + name);
		
		HttpClient client = HttpClientBuilder.create().build();
		
		HttpResponse response = client.execute(request);
		
		ObjectMapper mapper = new ObjectMapper();
		
		user = mapper.readValue(response.getEntity().getContent(), User.class);
	    
	}
	
	@Then("^I get results$")
	public void i_get_results(DataTable userData) throws Throwable {

		List<List<String>> info = userData.raw();
		
		String name = info.get(0).get(1);
		String location = info.get(1).get(1);
		String company = info.get(2).get(1);
		
		User expectedUser = new User(name, location, company);
		
		assertTrue(user.equals(expectedUser));
	
	}

}
