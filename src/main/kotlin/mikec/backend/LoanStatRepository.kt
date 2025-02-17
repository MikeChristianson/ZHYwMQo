package mikec.backend

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.query.Param
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class LoanStatRepository @Autowired constructor(
    private val jdbcTemplate: NamedParameterJdbcTemplate,
) {

    fun summarize(): Map<String, Any> {
        return jdbcTemplate.jdbcOperations.queryForMap(
            """
        select min(loan_amnt), max(loan_amnt), min(int_rate), max(int_rate)
        from loan_stats
    """
        ).toMap();
    }

//    @Query("""
//        select min(loan_amnt), max(loan_amnt)
//        from loan_stats
//    """)
//    fun summarizeLoanAmnt(): Any

    fun summarize(@Param("type") type: String): Map<String, Any> {
        val query = """
        select min(:type), max(:type)
        from loan_stats
    """
        return jdbcTemplate.queryForMap(query, mapOf("type" to type)).toMap()
    }
}