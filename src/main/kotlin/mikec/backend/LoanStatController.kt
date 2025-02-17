package mikec.backend

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/loan-stats", produces = [MediaType.APPLICATION_JSON_VALUE])
class LoanStatController @Autowired constructor(
    private val repository: LoanStatRepository,
) {
    @GetMapping("/")
    fun summary(): Any {
        return repository.summarize()
    }

//    @GetMapping("/loan_amntz")
//    fun statsLoanAmnt(): Any {
//        val summary = repository.summarizeLoanAmnt()
//        val message = (summary as Array<*>).contentToString()
//        return message
//    }

    @GetMapping("/{type}")
    fun summaryForType(@PathVariable type: String): Any {
        return repository.summarize(type)
    }
}