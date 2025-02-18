package mikec.backend

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.query.Param
import org.springframework.jdbc.core.PreparedStatementCreator
import org.springframework.jdbc.core.RowCallbackHandler
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.ResultSet

@Repository
class LoanStatRepository @Autowired constructor(
    private val jdbcTemplate: NamedParameterJdbcTemplate,
) {

    fun summarize() {
        return jdbcTemplate.jdbcOperations.query(PreparedStatementCreator {
            val preparedStatement = it.prepareStatement(
                """
        select min(?), max(?), min(?), max(?)
        from loan_stats
    """
            )
            preparedStatement.setString(1, "loan_amnt")
            preparedStatement.setString(2, "loan_amnt")
            preparedStatement.setString(3, "int_rate")
            preparedStatement.setString(4, "int_rate")
            preparedStatement
        }, object : RowCallbackHandler {
            override fun processRow(rs: ResultSet) {
                rs.findColumn("fred")
            }
        })
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