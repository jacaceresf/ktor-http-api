package jacaceresf.dev

import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.Test
import kotlin.test.assertEquals

class OrderRouteTests {

    @Test
    fun testGetOrder() {
        ///this indicates that we want to run the application as a test
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Get, "/order/2021100201").apply {
                assertEquals(
                    """{"number":"2021100201","contents":[{"item":"Ham Sandwich","amount":1,"price":1.5},{"item":"Water","amount":3,"price":1.45},{"item":"Beer","amount":6,"price":3.75}]}""",
                    response.content
                )
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

}