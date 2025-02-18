package mikec.backend

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.ResultSet

@Repository
class LoanStatRepository @Autowired constructor(
    private val jdbcTemplate: JdbcTemplate,
) {
    private val columns = jdbcTemplate.query("show columns from loan_stats where Field <> 'id'") { rs, _ ->
        rs.getString("Field")
    }.toList()

    fun summarize(): Map<String?, List<Map.Entry<String, Any>>>? {
        return summarize(columns)
    }

    fun summarize(types: List<String>): Map<String?, List<Map.Entry<String, Any>>>? {
        val select = types
            .filter { it in columns }
            .map { type -> "min($type), max($type)" }

        val results = jdbcTemplate.query({
            it.prepareStatement(
                """
               select ${select.joinToString()}
               from loan_stats
           """
            )
        }, resultSetToMap).firstOrNull()

        return results
            ?.entries
            ?.groupBy(typeKeySelector)
    }

    fun summarize(type: String): List<Map<String, Any>> {
        return jdbcTemplate.query({
            it.prepareStatement(
                """
               select min($type), max($type)
               from loan_stats
           """
            )
        }, resultSetToMap)
    }

    val resultSetToMap: (rs: ResultSet, rowNum: Int) -> Map<String, Any> = { rs, _ ->
        buildMap {
            val metaData = rs.metaData
            for (i in 1..metaData.columnCount) {
                val key = metaData.getColumnName(i)
                val value = rs.getObject(i)
                set(key, value)
            }
        }
    }

    val typeKeySelector: (Map.Entry<String, Any>) -> String? = { (key, _) ->
        Regex(""".+\((.+)\)""")
            .matchEntire(key)
            ?.groups
            ?.get(1)
            ?.value
    }

}