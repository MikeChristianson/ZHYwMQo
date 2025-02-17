package mikec.backend

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/loan-stats", produces = [MediaType.APPLICATION_JSON_VALUE])
class LoanStatController @Autowired constructor(
    private val repository: LoanStatRepository,
) {
    @GetMapping("/summary")
    fun statsLoanAmnt(): Any {
        val summary = repository.summarize()
        val message = (summary as Array<*>).contentToString()
        return message
    }
}