package mikec.backend

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/loan-stats", produces = [MediaType.APPLICATION_JSON_VALUE])
class LoanStatController @Autowired constructor(
    private val repository: LoanStatRepository,
) {
    @GetMapping("/")
    fun summary(): Map<String?, List<Map.Entry<String, Any>>>? {
        return repository.summarize()
    }

    @GetMapping("/types")
    fun summaryForTypes(@RequestParam(required = true) types: List<String>): Map<String?, List<Map.Entry<String, Any>>>? {
        return repository.summarize(types)
    }

    @GetMapping("/{type}")
    fun summaryForType(@PathVariable type: String): Any {
        return repository.summarize(type)
    }
}