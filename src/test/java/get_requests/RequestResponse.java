package get_requests;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestResponse {

    /*
        1-Manuel API testi icin Postman kullaniyoruz.
        2-API otomasyon testi icin REST ASSURED kutuphanesini kullaniyoruz.
        3-Otomasyon kodlarin yazimi icin su adimlari izleriz :
            a-Gereksinimleri anlama, inceleme
            b-Test Senaryosunun yazilmasi
                *Test Case yazmak icin Gherkin dilini kullaniyoruz.
                *Given: End point , body ...
                *When: get,put,delete... gibi islemleri kullaniyoruz.
                *Then:Assertion, close ... islemleri
                *And:Coklu islemlerin art arda yapildigi zaman kullanilir.
            c-Otomasyon kodlarini yazarken su adimlari izleriz :
                *Set the URL
                *Set the expected data
                *Set the request and get the response
                *Do assertion
                *
     */

    public static void main(String[] args) {

        //Get test'i nasil yapilir.
        String url = "https://petstore.swagger.io/v2/pet/888";
        Response response = given().when().get(url);
        //  System.out.println(response);
        response.prettyPeek();

        System.out.println("*********************************************");

        //status code nasil yazdirilir
        System.out.println("Status code : "+response.statusCode());
        System.out.println("*********************************************");

        //Content type nasil yazdirilir.
        System.out.println("Content type : "+response.contentType());
        System.out.println("*********************************************");

        //status line nasil yazdirilir.
        System.out.println("Status line : "+response.statusLine());
        System.out.println("*********************************************");

        //Header'daki veriler nasil yazdirilir.
        System.out.println("Header icindeki server : "+response.header("Server"));
        System.out.println("*********************************************");

        System.out.println("Header icindeki connection : "+response.header("Connection"));
        System.out.println("*********************************************");

        //Headers nasil yazdirilir.
        System.out.println("Tum Headerlars : "+response.headers());
        System.out.println("*********************************************");

        //time nasil yazdirilir
        System.out.println("Time : "+response.time());
        System.out.println("*********************************************");



    }

}
