import groovy.mock.interceptor.StubFor
import org.example.CCApp
import org.example.CreditHistory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertFalse
import static org.junit.jupiter.api.Assertions.assertTrue

class StubForExampleTest {
    def creditHistoryStub

    @BeforeEach
    void init() {
        creditHistoryStub = new StubFor(CreditHistory)
        creditHistoryStub.demand.getCreditScore { ssn ->
            if(ssn == 123) return 400
            return 500
        }
    }

    @Test
    void testIsApproved() {
        def user1 = [ssn: 123]

        // user1 score = 400
        creditHistoryStub. use {
            def app = new CCApp()
            assertFalse app.isApproved(user1)
        }
    }

    @Test
    void testWithMap() {
        def creditHistory = [
                getCreditScore: { ssn->
                    if(ssn == 123) return 400
                    if(ssn == 12) return 700
                    return 500
                }
        ]

        def user1 = [ssn: 123]
        def user2 = [ssn: 12]
        def user3 = [ssn: 1234]

        def app = new CCApp()
        assertTrue app.isApproved2(creditHistory, user2)
        assertFalse app.isApproved2(creditHistory, user1)
        assertFalse app.isApproved2(creditHistory, user3)
    }

    @Test
    void testWithExpando() {
        def creditHistory = new Expando()
        creditHistory.getCreditScore = {ssn->
                    if(ssn == 123) return 400
                    if(ssn == 12) return 700
                    return 500
        }

        def user1 = [ssn: 123]
        def user2 = [ssn: 12]
        def user3 = [ssn: 1234]

        def app = new CCApp()
        assertTrue app.isApproved2(creditHistory, user2)
        assertFalse app.isApproved2(creditHistory, user1)
        assertFalse app.isApproved2(creditHistory, user3)
    }
}
