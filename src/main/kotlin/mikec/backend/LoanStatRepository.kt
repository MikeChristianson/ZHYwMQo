package mikec.backend

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Component

@Component
interface LoanStatRepository : JpaRepository<LoanStat, LoanId>, JpaSpecificationExecutor<LoanStat> {

    @Query("""
        select min(loan_amnt), max(loan_amnt)
        from loan_stats
    """, nativeQuery = true)
    fun summarize(): Any
    //given a table with four columns, how to calculate min, max, mean, median for each column, individually, using mysql?
}