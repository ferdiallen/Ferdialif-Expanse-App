package main.core.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Atm
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.DinnerDining
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Train
import androidx.compose.ui.graphics.vector.ImageVector

data class TransferCategory(
    val name: String,
    val icon: ImageVector
)

val listCategory = listOf(
    TransferCategory(
        name = "Income",
        icon = Icons.Filled.Atm
    ),
    TransferCategory(
        name = "Groceries",
        icon = Icons.Filled.ShoppingCart
    ),
    TransferCategory(
        name = "Daily Meal",
        icon = Icons.Filled.DinnerDining
    ),
    TransferCategory(
        name = "Electricity",
        icon = Icons.Filled.Bolt
    ),
    TransferCategory(
        name = "Train",
        icon = Icons.Filled.Train
    ),
    TransferCategory(
        name = "Date",
        icon = Icons.Filled.Favorite
    )
)