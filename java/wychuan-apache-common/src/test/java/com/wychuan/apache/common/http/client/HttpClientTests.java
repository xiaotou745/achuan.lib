package com.wychuan.apache.common.http.client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpClientTests {

	@Test
	public void getHtmlTest() throws URISyntaxException, ClientProtocolException, IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		HttpGet httpGet = new HttpGet("http://www.baidu.com");
		
		CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
		
		HttpEntity httpEntity = httpResponse.getEntity();
		
		String html = EntityUtils.toString(httpEntity);
		
		System.out.println(html);
		
		httpResponse.close();
		httpClient.close();
		
	}
}
