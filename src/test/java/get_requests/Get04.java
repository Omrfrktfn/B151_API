package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

public class Get04 extends JsonPlaceHolderBaseUrl {

    // Given  https://jsonplaceholder.typicode.com/todos
    // When   I send a GET request to the Url
    // And    Accept type is "application/json"
    // Then   HTTP Status Code should be 200
    // And    Response format should be "application/ison"
    // And    200 adet todos olmalı
    // And    başlıklarından birisi  "quis eius est sint explicabo" olmalı
    // And    userIds ler arasında 2, 7, and 9 bulunmalı

    @Test
    public void Get() {

        //1. Set url
        //  String url = "https://jsonplaceholder.typicode.com/todos";// tercih edilmeyen yöntem
        spec.pathParam("first", "todos");
        //2. set expected data
        //3. Send request
        Response response = given(spec).when().get("{first}");
        response.prettyPrint();
        // 4. Do assertion
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", hasSize(200),
                        "title", hasItem("quis eius est sint explicabo"),
                        "id", hasItems(2,7,9));
    }
}
