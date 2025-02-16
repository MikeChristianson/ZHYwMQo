package mikec.backend

import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.io.read
import org.springframework.stereotype.Service

@Service
class DataFrameService {
    private val df = DataFrame.read("./src/main/resources/db/LoanStats_securev1_2017Q4.csv")


}