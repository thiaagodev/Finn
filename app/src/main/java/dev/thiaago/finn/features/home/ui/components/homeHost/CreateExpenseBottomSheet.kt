package dev.thiaago.finn.features.home.ui.components.homeHost

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.thiaago.finn.core.ui.theme.FinnColors
import dev.thiaago.finn.features.home.domain.entities.AccountEntity
import dev.thiaago.finn.features.home.domain.entities.ReleaseEntity
import dev.thiaago.finn.features.home.domain.entities.ReleaseType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CreateExpenseBottomSheet(
    accounts: List<AccountEntity> = listOf(),
    onConfirm: (release: ReleaseEntity) -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {

        val pagerState = rememberPagerState(
            initialPage = 0,
            pageCount = { 2 }
        )

        val selectedTab by remember {
            derivedStateOf { pagerState.currentPage }
        }

        val pages = listOf(ReleaseType.EXPENSE, ReleaseType.INCOME)

        TabRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            selectedTabIndex = selectedTab,
            containerColor = Color.White,
            divider = {},
            indicator = { tabPositions ->
                Box(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[selectedTab])
                        .offset(y = 8.dp)
                        .padding(horizontal = 64.dp)
                        .background(
                            color = FinnColors.darkGreen,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .height(2.dp)

                )
            }
        ) {
            pages.forEachIndexed { index, releaseType ->
                val coroutineScope = rememberCoroutineScope()
                Tab(
                    interactionSource = object : MutableInteractionSource {
                        override val interactions: Flow<Interaction> = emptyFlow()
                        override suspend fun emit(interaction: Interaction) {}
                        override fun tryEmit(interaction: Interaction) = true
                    },
                    unselectedContentColor = Color.Black,
                    selectedContentColor = Color.Black,
                    selected = index == selectedTab,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                ) {
                    Text(
                        text = releaseType.translatedType(),
                        style = TextStyle(
                            fontSize = 16.sp
                        )
                    )
                }
            }
        }

        HorizontalPager(state = pagerState) { index ->
            val releaseType = pages[index]
            ExpenseForm(
                accounts = accounts,
                releaseType = releaseType,
                onConfirm = {
                    onConfirm(it)
                }
            )
        }
    }

}

@Preview(showSystemUi = true)
@Composable
private fun CreateExpenseBottomSheetPreview() {
    CreateExpenseBottomSheet() {

    }
}

