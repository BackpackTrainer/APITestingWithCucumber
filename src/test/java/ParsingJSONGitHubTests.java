import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ParsingJSONGitHubTests {
	
	User user;

	@Test
	public void testResponseCodeForMyAccount() throws ClientProtocolException, IOException {
		String name = "BackpackTrainer";
		
		HttpUriRequest request = new HttpGet("https://api.github.com/users/" + name);
		
		HttpClient client = HttpClientBuilder.create().build();
		
		HttpResponse response = client.execute(request);
		
		assertEquals(response.getStatusLine().getStatusCode(), (HttpStatus.SC_OK));
	}
	
	@Test
	public void testGettingTheNameForMyAccount() throws ClientProtocolException, IOException {
		String name = "BackpackTrainer";
		String expectedResult = "Bill Fairfield";
		
		HttpUriRequest request = new HttpGet("https://api.github.com/users/" + name);
		
		HttpClient client = HttpClientBuilder.create().build();
		
		HttpResponse response = client.execute(request);
		
		ObjectMapper mapper = new ObjectMapper();
		
		user = mapper.readValue(response.getEntity().getContent(), User.class);
		
		System.out.println("Actual User " + user.getName() + " " + user.getLocation() + " " + user.getCompany());
		
		assertEquals(expectedResult, user.getName());
	}

	@Test
	public void testGettingTheNumberOfRepositoriesForMyAccount() throws ClientProtocolException, IOException {
		String name = "BackpackTrainer";
		int expectedResult = 16;
		
		HttpUriRequest request = new HttpGet("https://api.github.com/users/" + name);
		
		HttpClient client = HttpClientBuilder.create().build();
		
		HttpResponse response = client.execute(request);
		
		ObjectMapper mapper = new ObjectMapper();
		
		user = mapper.readValue(response.getEntity().getContent(), User.class);
		
		assertEquals(expectedResult, user.getPublic_repos());
	}
}
