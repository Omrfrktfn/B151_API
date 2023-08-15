package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get07 extends JsonPlaceHolderBaseUrl {
    //Given
    //https://jsonplaceholder.typicode.com/todos
    //When
    //I send GET Request to the URL
    //        Then
    //   1)Status code is 200
    //        2)"Id"leri 190 dan büyük olanları konsola yazdırın
    //     "Id"si 190 dan büyük 10 adet veri olduğunu doğrulayın
    //   3)"Id"si 5 den küçük olan tüm kullanıcıların "userid"lerini yazdırın
    //   "Id"si 5 den küçük olan 4 kullanıcı olduğunu doğrulayın
    //   4)"Id" si 5 ten küçük tüm kullanıcıların "title" larını yazdırın
    //     "delectus aut autem" başlığının id numarası 5 den küçük bir kullanıcıya ait olduğunu doğrulayın


    @Test
    public void get07() {

        spec.pathParams("first", "todos");

        Response response = given().
                spec(spec).
                when().
                get("{first}");
        // response.prettyPrint();

        assertEquals(200, response.statusCode());

        System.out.println("**********************************");

        JsonPath json = response.jsonPath();
        System.out.println("title : " + json.getList("findAll{it.id>190}"));
        System.out.println("*************************************************");
        System.out.println("title : " + json.getList("findAll{it.id>190}.title"));
        System.out.println("*************************************************");
        System.out.println("title : " + json.getList("findAll{it.id>190}.userId"));
        //groovy lang list icindeki jsonlarda yapip data geri cagirmamizi saglar.

        System.out.println("**********************************");

        List<Integer> idList = json.getList("findAll{it.id>190}.Id");
        System.out.println("idlist -->" + idList);

        assertEquals(10, idList.size());

        System.out.println("**********************************");

        assertEquals(200, response.statusCode());

        System.out.println("**********************************");
        List<Integer> userIdList = json.getList("findAll{it.id<5}.userId");
        System.out.println("userid : " + userIdList);

        System.out.println("**********************************");
        assertEquals(4, userIdList.size());

        System.out.println("**********************************");
        List<String> titleList = json.getList("findAll{it.id<5}.title");
        System.out.println("titleList = " + titleList);

        // System.out.println(json.getList("findAll{it.title=='delectus aut autem'}"));
        // System.out.println("**********************************");
        // System.out.println(json.getList("findAll{it.title=='delectus aut autem'}.id"));

        assertTrue(titleList.contains("delectus aut autem"));
    }

}
