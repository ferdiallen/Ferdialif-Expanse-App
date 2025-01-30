package main.data.local

import main.core.util.TransferCategory

enum class TransferType {
    INCOME,
    OUTCOME
}

data class IncomeModel(
    val id: Int,
    val incomeName: String,
    val incomeValue: Long,
    val incomeDate: String,
    val type:TransferCategory
)
