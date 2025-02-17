package mikec.backend

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class LoanStatControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
) {

    @Test
    fun `hit summary endpoint`() {
        mockMvc.perform(
            get("/loan-stats/")
//                .header(HttpHeaders.ACCEPT, MediaType.ALL)
        )
            .andExpectAll(
                status().isOk,
                content().string("[1000, 40000, 5.32, 30.99]")
            )

    }

    @Test
    fun `hit loan_amnt endpoint`() {
        mockMvc.perform(
            get("/loan-stats/loan_amnt")
//                .header(HttpHeaders.ACCEPT, MediaType.ALL)
        )
            .andExpectAll(
                status().isOk,
                content().string("[1000, 40000]")
            )

    }
}