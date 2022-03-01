import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiTest{

    @Test
    public void checkEmailByFirstnameAndLastname() {
        assertEquals("george.bluth@reqres.in", Steps.getUserEmail("George", "Bluth"));
    }

    @Test
    public void checkEmailByFirstnameAndLastnamePagination() {
        assertEquals("michael.lawson@reqres", Steps.getUserEmail("Michael", "Lawson"));
    }
}
