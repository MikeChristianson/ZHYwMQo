package mikec.backend

import com.opencsv.CSVReaderHeaderAwareBuilder
import com.opencsv.enums.CSVReaderNullFieldIndicator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import java.util.stream.Collectors
import kotlin.streams.asStream

class CsvReaderTest {

    private lateinit var loanFile: File

    @BeforeEach
    fun setUp() {
        loanFile = File("./src/main/resources/static/LoanStats_securev1_2017Q4.csv")
    }

    @Test
    fun `verify can read all lines`() {
        loanFile.reader().use {
            val csvReader = CSVReaderHeaderAwareBuilder(it)
                .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_QUOTES)
                .build()
            assertThat(csvReader.count()).isEqualTo(118_636)
        }
    }

    @Test
    fun `can calculate max for loan amount`() {
        loanFile.reader().use { reader ->
            val csvReader = CSVReaderHeaderAwareBuilder(reader)
                .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_QUOTES)
                .build()

            val loanAmntSequence = generateSequence {
//                println("Peek: ${Arrays.toString(csvReader.peek())}")
                csvReader.readNext("loan_amnt", "funded_amnt")
            }

            val maxLoanMnt = loanAmntSequence
                .filterNot { it.isEmpty() }
                .map { it.first() }
                .map { it.toLong() }
                .maxByOrNull { it }

            assertThat(maxLoanMnt).isEqualTo(40_000)
        }
    }

    @Test
    fun `can calculate min max and avg for loan amount`() {
        loanFile.reader().use { reader ->
            val csvReader = CSVReaderHeaderAwareBuilder(reader)
                .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_QUOTES)
                .build()

            val columnNames = listOf("loan_amnt", "funded_amnt")

            val loanAmntSequence = generateSequence {
//                println("Peek: ${Arrays.toString(csvReader.peek())}")
                csvReader.readNext(*columnNames.toTypedArray())
            }

            val collect: MutableMap<Int, MutableList<Array<String>>> = loanAmntSequence
                .filter { it.isNotEmpty() }
                .asStream()
                .collect(Collectors.groupingBy { it.size })
            collect
        }
    }
}