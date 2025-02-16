package mikec.backend

import org.junit.jupiter.api.Test
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.io.*
import org.jetbrains.kotlinx.dataframe.api.*

class DataFrameTest {

    @Test
    fun dataframe() {
        var df = DataFrame.read("./src/main/resources/db/LoanStats_securev1_2017Q4.csv")

        println(df.describe())
        println(df.columnNames())
        println(df.count())

        println(
            df
                .sortBy("loan_amnt")
                .tail()
                .reverse()
        )

        df = df.convert("int_rate").to { column ->
            column.convertToString().map {
                it?.substringBeforeLast('%')?.toDouble()
            }
        }

        val described = df.describe("loan_amnt", "funded_amnt", "int_rate")
        println(described)
    }
}