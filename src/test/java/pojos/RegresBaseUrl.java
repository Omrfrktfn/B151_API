package pojos;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
public class RegresBaseUrl {

    protected RequestSpecification spec;
    //Tekrarlı methodlarda  kullanılan değerler burada yazılır. ayni olan ve tekrarli olan kodlar bu class'a yazilir.
    @Before
    public void setUp() {
        spec = new RequestSpecBuilder().
                setBaseUri("https://reqres.in/api").
                setContentType(ContentType.JSON).
                build();
    }
}
