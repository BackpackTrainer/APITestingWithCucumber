import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

public class SimpleAPITests {

	@Test
	public void testRandomUserDoesNotExist() throws ClientProtocolException, IOException{
		String name;
		
		name = RandomStringUtils.randomAlphabetic(8);
		HttpUriRequest request = new HttpGet("https://api.github.com/users/" + name);
		
		HttpClient client = HttpClientBuilder.create().build();
		
		HttpResponse response = client.execute(request);
		
		assertEquals(response.getStatusLine().getStatusCode(), (HttpStatus.SC_NOT_FOUND));
		
	}
	
	@Test
	public void testDefaultMIMEType() throws ClientProtocolException, IOException{
		String name = "BackpackTrainer";
		String expectedMimeType = "application/json";
		String actualMimeType;
		
		HttpUriRequest request = new HttpGet("https://api.github.com/users/" + name);
		
		HttpClient client = HttpClientBuilder.create().build();
		
		HttpResponse response = client.execute(request);
		
		actualMimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
		
		assertEquals(expectedMimeType, actualMimeType);
		
	}

}
