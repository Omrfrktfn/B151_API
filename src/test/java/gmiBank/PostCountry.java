package gmiBank;

import base_urls.GmiBankBaseUrl;
import org.junit.Test;

public class PostCountry extends GmiBankBaseUrl {

        /*
        https://app.swaggerhub.com/apis/yasinaniltechpro/GmiBank/0.0.1
        dokümanını kullanarak en az 3 "state"
        içeren bir "country" oluşturan bir otomasyon testi yazınız.
        Not : Autherization için headers'a "Authorization" = ""Bearer abc123"
        şeklinde Bearer token giriniz.
        */
    /*
   Given
        https://gmibank.com/api/tp-countries
    And
        Body:
            {
              "name": "Banana",
              "states": [
                {
                  "id": 1,
                  "name": "Apple"
                },
                {
                  "id": 2,
                  "name": "Orange"
                },
                {
                  "id": 3,
                  "name": "Pear"
                }
              ]
             }
    When
        send posr request
    Then
        Statuscode 200
    And
        body :
                 {
    "id": 191587,
    "name": "Banana",
    "states": [
        {
            "id": 1,
            "name": "Apple",
            "tpcountry": null
        },
        {
            "id": 2,
            "name": "Orange",
            "tpcountry": null
        },
        {
            "id": 3,
            "name": "Pear",
            "tpcountry": null
        }
    ]
}
     */

    @Test
    public void post() {
        spec.pathParams("first","api",
                "second","tp-countries");



    }

}
