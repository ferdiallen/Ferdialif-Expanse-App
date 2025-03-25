package main.ui.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ferdialifcountermoney.composeapp.generated.resources.Res
import ferdialifcountermoney.composeapp.generated.resources.amount
import ferdialifcountermoney.composeapp.generated.resources.category
import ferdialifcountermoney.composeapp.generated.resources.expense_title
import ferdialifcountermoney.composeapp.generated.resources.ferdialif_balance_title
import ferdialifcountermoney.composeapp.generated.resources.income
import ferdialifcountermoney.composeapp.generated.resources.no_transfer_list
import ferdialifcountermoney.composeapp.generated.resources.ok
import ferdialifcountermoney.composeapp.generated.resources.outcome
import ferdialifcountermoney.composeapp.generated.resources.select_category_hint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update
import main.core.font.poppinsFont
import main.core.ui.NumberTextField
import main.core.ui.ToastPopup
import main.core.ui.ToastPopupData
import main.core.ui.rememberMoneyPopup
import main.core.util.TransferCategory
import main.core.util.TransformDataMoney
import main.core.util.currencyResult
import main.core.util.listCategory
import main.core.util.retrieveCurrentDate
import main.data.local.IncomeModel
import main.ui.GreenTealColor
import org.jetbrains.compose.resources.stringResource
import org.kodein.emoji.Emoji
import org.kodein.emoji.compose.WithPlatformEmoji
import org.kodein.emoji.compose.m3.TextWithNotoAnimatedEmoji
import org.kodein.emoji.compose.m3.TextWithPlatformEmoji
import org.kodein.emoji.light
import org.kodein.emoji.people_body.hand_fingers_open.WavingHand
import kotlin.random.Random

class MainScreenViewModel : ViewModel() {
    private var _currentTransferData = MutableStateFlow<List<IncomeModel>>(emptyList())
    val currentTransferData = _currentTransferData.asStateFlow()

    private val _definedTransferLimit = MutableStateFlow(0L)
    val definedTransferLimit = _definedTransferLimit.asStateFlow()

    private val _definedMaxTransferLimit = MutableStateFlow(0L)
    val definedMaxTransferLimit = _definedMaxTransferLimit.asStateFlow()


    fun updateMaxTransferLimitData(data: Long) = _definedMaxTransferLimit.update {
        data
    }

    fun insertTransferData(data: IncomeModel) {
        _currentTransferData.update {
            val currentData = it.toMutableList()
            currentData.add(data)
            currentData.toList()
        }
    }

    fun addDataTransferLimit(data: Long) {
        _definedTransferLimit.update {
            data
        }
    }

}

private val moneyInput = listOf(5000, 10000, 15000, 20000, 25000, 50000, 100000, 1_000_000)

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class, ExperimentalComposeUiApi::class)
@Composable
fun MainCalculationScreen(modifier: Modifier = Modifier) {
    val viewModel: MainScreenViewModel = viewModel {
        MainScreenViewModel()
    }
    var shouldHideUi by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(Unit) {
        delay(2000L)
        viewModel.addDataTransferLimit(0L)
        shouldHideUi = true
    }
    val currentTransferData by viewModel.currentTransferData.collectAsStateWithLifecycle()
    var shouldShowUpdateAccountDialog by remember {
        mutableStateOf(false)
    }
    val animateBlurValue by animateDpAsState(if (shouldShowUpdateAccountDialog) 10.dp else 0.dp)
    val transferLimit by viewModel.definedTransferLimit.collectAsStateWithLifecycle()
    val maxTransferLimit by viewModel.definedMaxTransferLimit.collectAsStateWithLifecycle()
    val remainingTransferLimit = remember(transferLimit, maxTransferLimit) {
        (transferLimit.toDouble() / maxTransferLimit.toDouble())
    }
    val animateCurrentProgress by animateFloatAsState(
        if (remainingTransferLimit.isNaN()) 0F else remainingTransferLimit.toFloat(), tween(400)
    )
    var currentBalance by remember {
        mutableLongStateOf(0)
    }
    val density = LocalDensity.current
    val windowSize = LocalWindowInfo.current
    val calculateOutcomeResult = remember(currentTransferData) {
        currentTransferData.filter {
            it.type.name != "Income"
        }.sumOf {
            it.incomeValue
        }
    }
    val calculateIncomeResult = remember(currentTransferData) {
        currentTransferData.filter {
            it.type.name == "Income"
        }.sumOf {
            it.incomeValue
        }
    }
    val data = rememberMoneyPopup()
    Scaffold(modifier, floatingActionButton = {
        Column(horizontalAlignment = Alignment.End) {
            SmallFloatingActionButton(
                onClick = {},
                containerColor = GreenTealColor
            ) {
                Icon(
                    Icons.Filled.AttachMoney, contentDescription = "",
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            FloatingActionButton(onClick = {
                shouldShowUpdateAccountDialog = true
            }, containerColor = Color(0xFFE9C23E)) {
                Row {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "",
                        tint = Color.White
                    )
                    Text("")
                }
            }
        }
    }) {
        Column(
            Modifier.fillMaxSize().blur(animateBlurValue)
                .padding(top = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF2FB8A7)
                ),
                modifier = Modifier.fillMaxWidth(0.9F)
            ) {
                Box {
                    Column(
                        modifier = Modifier.fillMaxWidth(0.95F).padding(top = 14.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        Card(
                            modifier = Modifier.size(30.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White.copy(
                                    0.5F
                                )
                            ),
                            shape = CircleShape
                        ) {

                        }
                    }

                    Column(
                        modifier = Modifier.fillMaxWidth(0.95F)
                            .padding(top = 54.dp, end = 64.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        Card(
                            modifier = Modifier.size(40.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White.copy(
                                    0.5F
                                )
                            ),
                            shape = CircleShape
                        ) {

                        }
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth(0.9F)
                            .padding(horizontal = 16.dp, vertical = 16.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            stringResource(Res.string.ferdialif_balance_title),
                            fontSize = 16.sp,
                            color = Color.White
                        )
                        Text(
                            currencyResult(currentBalance),
                            fontSize = 35.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White,
                            maxLines = 1
                        )
                    }

                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth(0.9F)) {
                Text("Transfer limit", color = Color.Gray)
            }
            Spacer(modifier = Modifier.height(4.dp))
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth(0.9F), progress = {
                animateCurrentProgress
            }, color = Color(0xFF2FB8A7))
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(0.9F)
            ) {
                Icon(Icons.Filled.ArrowDropUp, tint = Color.Green, contentDescription = "")
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    "${currencyResult(transferLimit)} | ${currencyResult(maxTransferLimit)} (${
                        if (remainingTransferLimit.isNaN()) {
                            0
                        } else {
                            remainingTransferLimit.times(
                                100
                            )
                        }
                    }%)",
                    fontSize = 14.sp
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(0.9F).padding(horizontal = 16.dp)) {
                Card(
                    modifier = Modifier.fillMaxWidth()
                        .height(70.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF105D38))
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Column(
                            modifier = Modifier.weight(1F),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(stringResource(Res.string.income), color = Color.White)
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                currencyResult(calculateIncomeResult),
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        VerticalDivider(modifier = Modifier.fillMaxHeight(0.6F))
                        Column(
                            modifier = Modifier.weight(1F),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(stringResource(Res.string.outcome), color = Color.White)
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                currencyResult(calculateOutcomeResult),
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                    }
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(
                    top = if (currentTransferData.isNotEmpty()) 32.dp else 0.dp
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                if (currentTransferData.isEmpty()) {
                    item {
                        SelectionContainer {
                            Text(stringResource(Res.string.no_transfer_list), fontSize = 17.sp)
                        }
                    }
                } else {
                    items(currentTransferData, key = {
                        it.id
                    }) {
                        Card(
                            modifier = Modifier.fillMaxWidth(0.9F)
                                .animateItem(
                                    fadeInSpec = tween(500),
                                    placementSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy)
                                ),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFECF7F7)
                            )
                        ) {
                            Box {
                                Column(
                                    modifier = Modifier.padding(
                                        horizontal = 16.dp,
                                        vertical = 6.dp
                                    )
                                ) {
                                    Text(
                                        it.incomeDate,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Light
                                    )
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            it.type.name,
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Light,
                                            color = if (it.type == listCategory[0]) Color(
                                                0xFF47B86D
                                            )
                                            else Color(0xFFBE4B4C),
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                        Spacer(Modifier.weight(1F))
                                        Text(
                                            (if (it.type.name == "Income") "+ " else "- ") +
                                                    currencyResult(
                                                        it.incomeValue
                                                    ),
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Light,
                                            color = if (it.type == listCategory[0]) Color(
                                                0xFF47B86D
                                            )
                                            else Color(0xFFBE4B4C),
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis

                                        )
                                        Spacer(modifier = Modifier.width(14.dp))
                                        Icon(
                                            imageVector = it.type.icon,
                                            contentDescription = "",
                                            modifier = Modifier.size(25.dp)
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(16.dp))
                                }

                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }

            }
        }

    }
    AnimatedVisibility(!shouldHideUi, exit = fadeOut(tween(600))) {
        Box(modifier = Modifier.fillMaxSize().background(color = Color(0xFF2a302b))) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                TextWithNotoAnimatedEmoji(
                    "Welcome ${Emoji.WavingHand.light}",
                    fontSize = 45.sp,
                    color = Color.White,
                    fontFamily = poppinsFont()
                )
            }
        }
    }
    AnimatedVisibility(
        shouldShowUpdateAccountDialog, enter = fadeIn(tween(400)), exit = fadeOut(
            tween(400)
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            var amountEntered = rememberSaveable {
                mutableStateOf("")
            }
            Box(
                modifier = Modifier.fillMaxSize().background(Color.Black.copy(0.4F))
                    .clickable(interactionSource = remember {
                        MutableInteractionSource()
                    }, indication = null) {
                        shouldShowUpdateAccountDialog = false
                    }) {

            }
            AnimatedVisibility(
                visible = this@AnimatedVisibility.transition.currentState.name == "Visible",
                enter = fadeIn(tween(100)) + slideInVertically(
                    tween(400, easing = LinearOutSlowInEasing),
                    initialOffsetY = {
                        -100
                    }
                ), modifier = Modifier.fillMaxHeight()
            ) {
                Card(
                    modifier = Modifier
                        .width(
                            with(density) {
                                when {
                                    windowSize.containerSize.width.toDp() > 403.dp -> 400.dp
                                    else -> 300.dp
                                }
                            }
                        ).wrapContentHeight(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    var selectedCategory: TransferCategory? by remember {
                        mutableStateOf(null)
                    }
                    Box(modifier = Modifier.wrapContentHeight()) {
                        Column(modifier = Modifier.padding(horizontal = 6.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(top = 14.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    stringResource(Res.string.expense_title),
                                    fontSize = 17.sp,
                                    maxLines = 1
                                )

                            }
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 32.dp, bottom = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                stringResource(Res.string.amount),
                                modifier = Modifier.fillMaxWidth(0.9F)
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Card(
                                modifier = Modifier.fillMaxWidth(0.9F),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFEBF8F8))
                            ) {
                                NumberTextField(modifier = Modifier
                                    .padding(vertical = 6.dp)
                                    .fillMaxWidth(0.8F)
                                    .height(35.dp), onUpdate = {
                                        amountEntered.value = it
                                }, amountEntered = {
                                    amountEntered.value
                                })
                            }
                            Spacer(modifier = Modifier.height(14.dp))
                            LazyColumn(modifier = Modifier.fillMaxWidth(0.9F)) {
                                moneyInput.chunked(3).forEach {
                                    item {
                                        LazyRow(modifier = Modifier.heightIn(40.dp)) {
                                            items(it) { harga ->
                                                Card(
                                                    onClick = {
                                                        if (amountEntered.value.isNotEmpty()) {
                                                            amountEntered.value = ""
                                                        }
                                                        amountEntered.value = harga.toString()
                                                    }, colors = CardDefaults.cardColors(
                                                        containerColor = Color(0xFF2FB8A7)
                                                    )
                                                ) {
                                                    Text(
                                                        currencyResult(harga.toLong()),
                                                        fontSize = 12.sp,
                                                        maxLines = 1,
                                                        modifier = Modifier.padding(
                                                            horizontal = 6.dp,
                                                            vertical = 6.dp
                                                        ),
                                                        fontWeight = FontWeight.SemiBold,
                                                        color = Color.White
                                                    )
                                                }
                                                Spacer(modifier = Modifier.width(4.dp))
                                            }
                                        }
                                    }
                                }
                            }
                            Text(
                                stringResource(Res.string.category),
                                modifier = Modifier.fillMaxWidth(0.9F)
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Card(
                                modifier = Modifier.fillMaxWidth(0.9F),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFEBF8F8))
                            ) {
                                var isDropDownExpanded by remember {
                                    mutableStateOf(false)
                                }
                                var xButtonPosition by remember {
                                    mutableStateOf(0.dp)
                                }
                                Row(
                                    modifier = Modifier.fillMaxWidth().padding(
                                        vertical = 12.dp,
                                        horizontal = 6.dp
                                    ),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    androidx.compose.animation.AnimatedVisibility(
                                        selectedCategory != null,
                                        enter = slideInHorizontally(tween(200))
                                    ) {
                                        Icon(
                                            selectedCategory?.icon ?: return@AnimatedVisibility,
                                            contentDescription = "",
                                            tint = when (selectedCategory) {
                                                listCategory[5] -> Color.Red
                                                else -> Color.Black
                                            }
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        when {
                                            selectedCategory == null -> stringResource(Res.string.select_category_hint)
                                            else -> selectedCategory?.name ?: ""
                                        },
                                        color = when {
                                            selectedCategory == null -> Color.LightGray
                                            else -> Color.Black
                                        },
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Spacer(modifier = Modifier.weight(1F))
                                    IconButton(onClick = {
                                        isDropDownExpanded = !isDropDownExpanded
                                    }, modifier = Modifier.size(20.dp).onGloballyPositioned {
                                        with(density) {
                                            xButtonPosition = it.positionInParent().x.toDp() + 50.dp
                                        }
                                    }) {
                                        Icon(
                                            Icons.Filled.ArrowDropDown, "",
                                            tint = Color.Gray
                                        )
                                    }
                                }
                                DropdownMenu(
                                    expanded = isDropDownExpanded,
                                    onDismissRequest = {
                                        isDropDownExpanded = false
                                    },
                                    offset = DpOffset(
                                        xButtonPosition, 200.dp
                                    )
                                ) {
                                    listCategory.forEach {
                                        DropdownMenuItem(text = {
                                            Text(it.name)
                                        }, onClick = {
                                            selectedCategory = it
                                            isDropDownExpanded = false
                                        })
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(14.dp))
                            Button(
                                onClick = {
                                    if (selectedCategory == null) {
                                        data.updateNewestData(
                                            ToastPopupData(
                                                message = "Category not selected",
                                                icon = Icons.Filled.Warning,
                                                duration = 2000L,
                                                shouldShow = true
                                            )
                                        )

                                    }
                                    currentBalance = try {
                                        selectedCategory?.let {
                                            return@let if (currentBalance > 0 && (amountEntered.value.toString()
                                                    .takeIf { it != "" }?.toLong() ?: 0L) == 0L
                                            ) {
                                                currentBalance
                                            } else {
                                                val result =
                                                    if (selectedCategory?.name == "Income") {
                                                        currentBalance + (amountEntered.value.toString()
                                                            .takeIf { it != "" }?.toLong()
                                                            ?: 0L)
                                                    } else {
                                                        currentBalance - (amountEntered.value.toString()
                                                            .takeIf { it != "" }?.toLong()
                                                            ?: 0L)
                                                    }
                                                result
                                            }
                                        } ?: return@Button
                                    } catch (_: Exception) {
                                        currentBalance
                                    }
                                    if ((amountEntered.value.toString()
                                            .takeIf { it != "" }?.toLong() ?: 0) > 0
                                    ) {
                                        selectedCategory?.let {
                                            viewModel.insertTransferData(
                                                IncomeModel(
                                                    id = Random.nextInt(),
                                                    incomeName = "-",
                                                    incomeValue = amountEntered.value.toString()
                                                        .toLong(),
                                                    incomeDate = retrieveCurrentDate(),
                                                    type = it
                                                )
                                            )
                                            shouldShowUpdateAccountDialog = false
                                        }
                                        return@Button
                                    }

                                },
                                modifier = Modifier
                                    .fillMaxWidth(0.5F),
                                shape = CircleShape,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFE9C23E)
                                )
                            ) {
                                Text(
                                    stringResource(Res.string.ok), fontSize = 14.sp,
                                    modifier = Modifier.offset {
                                        IntOffset(0, -2.dp.roundToPx())
                                    }.padding(vertical = 2.dp)
                                )
                            }
                        }

                    }
                }

            }

        }
    }
    ToastPopup(modifier = Modifier.fillMaxSize(), state = data) {
    }
}
