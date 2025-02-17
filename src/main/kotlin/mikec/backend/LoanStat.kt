package mikec.backend

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

@Entity
@Table(name = "loan_stats", schema = "backend")
open class LoanStat {
    @Id
    @Column(name = "id", nullable = false)
    open var id: LoanId? = null

    @NotNull
    @Column(name = "loan_amnt", nullable = false)
    open var loanAmnt: Long? = null

    @NotNull
    @Column(name = "int_rate", nullable = false, precision = 5, scale = 2)
    open var intRate: BigDecimal? = null
}

typealias LoanId = Long