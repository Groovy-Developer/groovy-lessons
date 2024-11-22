import org.example.PerfectNumber
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.*

class PerfectNumberTest {
    static def perfectNumber

    @BeforeAll
    static void beforeAll(){
        perfectNumber = new PerfectNumber()
    }



    @Test
    void testIsPerfect() {
        assertTrue perfectNumber.isPerfect(6)
        assertFalse perfectNumber.isPerfect(4)
    }
}
