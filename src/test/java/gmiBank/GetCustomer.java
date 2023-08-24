package gmiBank;

import base_urls.GmiBankBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.gmiBankPojos.AccountsPojo;
import pojos.gmiBankPojos.CountryPojo;
import pojos.gmiBankPojos.CustomerPojo;
import pojos.gmiBankPojos.UserPojo;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class GetCustomer extends GmiBankBaseUrl {
    /*
    Given
        https://www.gmibank.com/api/tp-customers/133986
    When
        User sends Get request
    Then
        Status code should be 200
     And
        Response body should be like:
{
    "id": 133986,
    "firstName": "Danika",
    "lastName": "Huel",
    "middleInitial": "S",
    "email": "danikahuel@gmail.com",
    "mobilePhoneNumber": "155-489-7844",
    "phoneNumber": "155-489-7844",
    "zipCode": "32476",
    "address": "3848 Lang Hill",
    "city": "Free City",
    "ssn": "725-97-6213",
    "createDate": "2022-01-21T05:00:00Z",
    "zelleEnrolled": false,
    "country": {
        "id": 187679,
        "name": "Banana",
        "states": null
    },
    "state": "Apple",
    "user": {
        "id": 134701,
        "login": "raymundo.moen",
        "firstName": "Danika",
        "lastName": "Huel",
        "email": "danikahuel@gmail.com",
        "activated": true,
        "langKey": "en",
        "imageUrl": null,
        "resetDate": null
    },
    "accounts": [
        {
            "id": 128481,
            "description": "Description",
            "balance": 0,
            "accountType": "CHECKING",
            "accountStatusType": "ACTIVE",
            "createDate": "2022-01-04T21:00:00Z",
            "closedDate": "2022-01-04T21:00:00Z",
            "employee": null,
            "accountlogs": null
        },
        {
            "id": 131776,
            "description": "mfy",
            "balance": 536846,
            "accountType": "CREDIT_CARD",
            "accountStatusType": "ACTIVE",
            "createDate": "2022-01-18T21:00:00Z",
            "closedDate": "2022-01-18T21:00:00Z",
            "employee": null,
            "accountlogs": null
        }
    ]
}
     */

    @Test
    public void getCustomer() {

        spec.pathParams("first", "api",
                "second", "tp-customers",
                "third", 133986);

        CountryPojo country = new CountryPojo("Banana", null);

        UserPojo user = new UserPojo(134701, "raymundo.moen", "Danika",
                "Huel", "danikahuel@gmail.com", true, "en", null, null);

        AccountsPojo account1 = new AccountsPojo(128481, "Description",
                0, "CHECKING", "ACTIVE",
                "2022-01-04T21:00:00Z", "2022-01-04T21:00:00Z", null, null);

        AccountsPojo account2 = new AccountsPojo(131776, "mfy", 536846, "CREDIT_CARD"
                , "ACTIVE", "2022-01-18T21:00:00Z", "2022-01-18T21:00:00Z", null, null);

        List<AccountsPojo> accounts = new ArrayList<>();
        accounts.add(account1);
        accounts.add(account2);

        CustomerPojo expectedData = new CustomerPojo(133986, "Danika", "Huel", "S", "danikahuel@gmail.com",
                "155-489-7844", "155-489-7844", "32476", "3848 Lang Hill",
                "Free City", "725-97-6213", "2022-01-21T05:00:00Z", false, country, "Apple", user, accounts);

        Response response = given(spec).
                when().
                get("{first}/{second}/{third}");
        response.prettyPrint();

        CustomerPojo actualData = convertJsonToJava(response.asString(), CustomerPojo.class);

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.getFirstName(), actualData.getFirstName());
        assertEquals(expectedData.getLastName(), actualData.getLastName());
        assertEquals(expectedData.getMiddleInitial(), actualData.getMiddleInitial());
        assertEquals(expectedData.getEmail(), actualData.getEmail());
        //gerisi odev


        assertEquals(country.getName(), actualData.getCountry().getName());
        assertEquals(country.getStates(), actualData.getCountry().getStates());

        assertEquals(user.getFirstName(), actualData.getUser().getFirstName());
        assertEquals(user.getLastName(), actualData.getUser().getLastName());
        assertEquals(user.getLogin(), actualData.getUser().getLogin());
        //odev

        assertEquals(account1.getDescription(), actualData.getAccounts().get(0).getDescription());
        assertEquals(account1.getBalance(), actualData.getAccounts().get(0).getBalance());
        assertEquals(account1.getAccountType(), actualData.getAccounts().get(0).getAccountType());

        assertEquals(account2.getDescription(), actualData.getAccounts().get(1).getDescription());
        assertEquals(account2.getBalance(), actualData.getAccounts().get(1).getBalance());
        assertEquals(account2.getAccountType(), actualData.getAccounts().get(1).getAccountType());


    }
}
