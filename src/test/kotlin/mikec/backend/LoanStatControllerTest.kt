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
        )
            .andExpectAll(
                status().isOk,
                content().string("""[{"min(loan_amnt)":1000,"max(loan_amnt)":40000,"min(int_rate)":5.32,"max(int_rate)":30.99}]""")
            )

    }

    @Test
    fun `hit multiple endpoint`() {
        mockMvc.perform(
            get("/loan-stats/types?types=loan_amnt,int_rate")
        )
            .andExpectAll(
                status().isOk,
                content().string("""{"loan_amnt":[{"min(loan_amnt)":1000},{"max(loan_amnt)":40000}],"int_rate":[{"min(int_rate)":5.32},{"max(int_rate)":30.99}]}""")
            )

    }

    @Test
    fun `hit variable type endpoint`() {
        mockMvc.perform(
            get("/loan-stats/loan_amnt")
        )
            .andExpectAll(
                status().isOk,
                content().string("""[{"min(loan_amnt)":1000,"max(loan_amnt)":40000}]""")
            )

    }

    @Test
    fun `hit variable type endpoint int`() {
        mockMvc.perform(
            get("/loan-stats/int_rate")
        )
            .andExpectAll(
                status().isOk,
                content().string("""[{"min(int_rate)":5.32,"max(int_rate)":30.99}]""")
            )

    }
}