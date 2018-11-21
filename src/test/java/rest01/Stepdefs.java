package rest01;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.containsString;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;


import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;



public class
Stepdefs {
    private static final String CREATE_PATH = "/admin";
    private static final String APPLICATION_JSON = "application/json";

    // private final InputStream jsonInputStream = this.getClass().getClassLoader().getResourceAsStream("cucumber.json");
    // private final String jsonString = new Scanner(jsonInputStream, "UTF-8").useDelimiter("\\Z").next();
    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    protected HttpResponse httpResponse = null;

    @When("^users want to get a admin$")
    public void users_want_to_get_a_admin() throws Exception {
        HttpGet request = new HttpGet("http://infbjvm245:" + "8080" + "/admin/all");
        request.addHeader("accept", APPLICATION_JSON);
        httpResponse = httpClient.execute(request);

    }

    @Then("^all admin users should pass$")
    public void all_admin_users_should_pass() throws Exception {
        // Write code here that turns the phrase above into concrete actions
        // throw new PendingException();
        String responseString = convertResponseToString(httpResponse);
        assertThat(responseString, containsString("\"username\":\"thor\""));
        assertEquals(200, httpResponse.getStatusLine().getStatusCode());
    }

    private String convertResponseToString(HttpResponse response)  throws IOException {
        InputStream responseStream = response.getEntity().getContent();
        Scanner scanner = new Scanner(responseStream, "UTF-8");
        String responseString = scanner.useDelimiter("\\Z").next();
        scanner.close();
        return responseString;
    }

}