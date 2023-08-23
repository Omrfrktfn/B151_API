package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class GmiBankBaseUrl {

    protected RequestSpecification spec;

    @Before
    public void setUp() {
        spec = new RequestSpecBuilder().
                setContentType(ContentType.JSON).
                addHeader("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYXJrX3R3YWluIiwiYXV0aCI6IlJPTEVfQURNSU4sUk9MRV9NQU5BR0VSIiwiZXhwIjoxNjkyODcyNDk1fQ.vEQLCz9olkzGxfn1JDf3xiQAmGjDsMSbn0eamoSI646J2qqXJGS7srh37a7ujIURBWjMPg5ZiFO4G-tVDPTHOw").
                setBaseUri("https://gmibank.com/api/tp-countries").
                build();
    }

}
