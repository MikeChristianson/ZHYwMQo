package mikec.backend

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@Suppress("UNCHECKED_CAST")
@SpringBootTest
class JpaTest @Autowired constructor(
    private val repository: LoanStatRepository
) {
    @Test
    fun name() {
        val summary = repository.summarize()
        val message = Arrays.toString((summary as? Array<Any>))
        println(message)
    }
}