package mikec.backend

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.assertj.MockMvcTester
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class LoanStatControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val mockMvcTester: MockMvcTester,
    private val loanStateController: LoanStatController,
) {

    @Test
    fun name() {

        mockMvc.perform(
            get("/loan-stats/summary")
//                .header(HttpHeaders.ACCEPT, MediaType.ALL)
        )
            .andExpectAll(
                status().isOk,
                content().string("[1000, 40000]")
            )

    }
}