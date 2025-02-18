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
    fun summary(): StatsByType? = repository.summarize()

    @GetMapping("/types")
    fun summaryForTypes(@RequestParam types: List<String>): StatsByType? =
        repository.summarize(types)

    @GetMapping("/{type}")
    fun summaryForType(@PathVariable type: String): Any = repository.summarize(type)
}

typealias StatsByType = Map<String?, List<Map.Entry<String, Any>>>
