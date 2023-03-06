package ApiTests;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;


public class AccountFeatureSteps {

    private static final String USER_ID = "63ed010e-277d-49aa-a9e2-49aac451c678";
    private static final String USERNAME = "Adelin Moldovan";
    private static final String PASSWORD = "Adem123*";
    private static final String BASE_URL = "https://demoqa.com";

    private static String token;
    private static Response response;

    @Given("User is an authorized user")
    public void userIsAnAuthorizedUser() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        response = request.body("{ \"userName\":\"" + USERNAME + "\", \"password\":\"" + PASSWORD + "\"}")
                .post("/Account/v1/GenerateToken");

        String jsonString = response.asString();
        token = JsonPath.from(jsonString).get("token");
    }


    @Given("User is on demoQA website")
    public void userIsOnDemoQAWebsite() {
        RestAssured.baseURI ="https://demoqa.com";
    }

    @When("User creates a new user using the POST method")
    public void userCreatesANewUserUsingThePOSTMethod() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        response = request.body("{ \"userName\":\"" + USERNAME + "\", \"password\":\"" + PASSWORD + "\"}")
                .post("/Account/v1/User");
        Response response = request.get("/Account/v1/User");
        var body = response.getBody();
        System.out.println("Response Body is: " + body.asString());

        int statusCode = response.getStatusCode();
        Assert.assertEquals(200, statusCode);
        System.out.println("Status Code is: " + response.getStatusLine());
    }

    @And("Status Response is {int}")
    public void statusResponseIs(int arg0) {
        int statusCode = response.getStatusCode();
        Assert.assertEquals(200, statusCode);
        System.out.println("Status Code is: " + response.getStatusCode());
    }


    @When("User generates token")
    public void UserGeneratesToken() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        response = request.body("{ \"userName\":\"" + USERNAME + "\", \"password\":\"" + PASSWORD + "\"}")
                .post("/Account/v1/GenerateToken");

        String jsonString = response.asString();
        token = JsonPath.from(jsonString).get("token");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(200, statusCode);
        System.out.println("Status Code is: " + response.getStatusLine());
    }

    @Then("Token is generated successfully")
    public void tokenIsGeneratedSuccessfully() {
        int statusCode = response.getStatusCode();
        Assert.assertEquals(200, statusCode);
        System.out.println("Status Code is: " + response.getStatusLine());
        System.out.println("Generated Token is: " + token);
    }

    @When("User makes a POST method for authorization")
    public void userMakesAPOSTMethodForAuthorization() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        response = request.body("{ \"userName\":\"" + USERNAME + "\", \"password\":\"" + PASSWORD + "\"}")
                .post("/Account/v1/Authorized");

        Response response = request.get("Account/v1/Authorized");
        ResponseBody body = response.getBody();
        System.out.println("Response Body is: " + body.asString());
    }

    @And("Status Response will be {int}")
    public void statusResponseWillBe(int arg0) {
        int statusCode = response.getStatusCode();
        Assert.assertEquals(200, statusCode);
        System.out.println("Status Code is: " + response.getStatusLine());
    }

    @And("Response header will be displayed")
    public void responseHeaderWillBeDisplayed() {
        String connection = response.header("connection");
        System.out.println("Connection is: " + connection);
        Assert.assertEquals("keep-alive",connection);

        String contentLength = response.header("content-length");
        System.out.println("Content-length is: " + contentLength);
        Assert.assertEquals("4", contentLength);

        String serverType = response.header("Server");
        System.out.println("Server value: " + serverType);
        Assert.assertEquals("nginx/1.17.10 (Ubuntu)",serverType);
    }


    @When("User deletes a user")
    public void userDeletesAUser() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        response = request.body("{ \"userId\":\"" + USER_ID + "\"}")
                .delete("/Account//AccountV1UserByUserIdDelete");

        Response response = request.delete("/Account/AccountV1UserByUserIdDelete");
    }

    @Then("User is successfully deleted")
    public void userIsSuccessfullyDeleted(int arg0) {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        response = request.body("{ \"userName\":\"" + USERNAME + "\", \"password\":\"" + PASSWORD + "\"}")
                .delete("/Account/AccountV1UserByUserIdDelete");

        Response response = request.delete("/Account/AccountV1UserByUserIdDelete");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(200, statusCode);
        System.out.println("Status Code is: " + response.getStatusLine());
    }

}
