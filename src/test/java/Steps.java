import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Steps extends BaseTest {

    public static String getUserEmail(String firstName, String lastName) {
        int pageNumber = 1;
        int totalPages;
        String result = null;

        search: do {
            Response response =
                    requestSpecification
                            .queryParam("page", pageNumber)
                            .when().get()
                            .then().statusCode(200)
                            .extract().response();

            JsonPath jsonPath = response.jsonPath();
            int count = jsonPath.getInt("data.size()");
            totalPages = jsonPath.getInt("total_pages");

            for (int i = 0; i < count; i++) {
                if (jsonPath.getString("data[" + i + "].first_name").equals(firstName) && jsonPath.getString("data[" + i + "].last_name").equals(lastName)) {
                    result = jsonPath.getString("data[" + i + "].email");
                    break search;
                }
            }
            pageNumber++;
        } while (pageNumber <= totalPages);
        return result;
    }
}
