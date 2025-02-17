package mikec.backend

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Component

@Component
interface LoanStatRepository : JpaRepository<LoanStat, LoanId>, JpaSpecificationExecutor<LoanStat> {

    @Query("""
        select min(loan_amnt), max(loan_amnt), min(int_rate), max(int_rate)
        from loan_stats
    """, nativeQuery = true)
    fun summarize(): Any

    @Query("""
        select min(loan_amnt), max(loan_amnt)
        from loan_stats
    """, nativeQuery = true)
    fun summarizeLoanAmnt(): Any

    @Query("""
        select min(:type), max(:type)
        from loan_stats
    """, nativeQuery = true)
    fun summarize(@Param("type") type: String): Any
}