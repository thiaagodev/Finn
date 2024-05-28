package dev.thiaago.finn.core.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.thiaago.finn.core.ui.theme.FinnColors
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InstallmentsBottomSheet(
    onSelected: (Int) -> Unit = {},
) {
    val parcels = 1..78
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { parcels.last },
    )

    val coroutineScope = rememberCoroutineScope()

    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Número de Parcelas",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        VerticalPager(
            state = pagerState,
            modifier = Modifier.size(128.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            pageSize = PageSize.Fixed(64.dp),
            pageSpacing = 1.dp,
        ) { index ->
            Box(
                modifier = Modifier
                    .clickable {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                    .size(48.dp)
                    .background(
                        color = if (pagerState.currentPage == index)
                            FinnColors.moneyColor else Color.White,
                        shape = CircleShape,
                    ),
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "${parcels.elementAt(index)}",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = if (pagerState.currentPage == index)
                            Color.White else Color.Black,
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        SimpleButton(text = "OK") {
            onSelected(pagerState.currentPage)
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun InstallmentsBottomSheetPreview() {
    InstallmentsBottomSheet()
}