package dev.thiaago.finn.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.thiaago.finn.core.ui.theme.FinnColors

@Composable
fun ChoicesBottomSheet(
    title: String,
    items: List<String>,
    onSelected:(value: String, index: Int) -> Unit = { _: String, _: Int -> },
    onClose: () -> Unit = {}
) {
    LazyColumn(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            BottomSheetHeader(title = title, onClose = onClose)
        }
        item { Spacer(modifier = Modifier.height(32.dp)) }
        items(items.size) { index ->
            Row(
                Modifier
                    .clickable {
                        onSelected(items[index], index)
                        onClose()
                    }
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 16.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                FinnColors.darkGreen,
                                FinnColors.lightGreen
                            )
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )
            ) {
                Text(
                    text = items[index],
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    ),
                    modifier = Modifier.padding(16.dp)
                )
            }

            if(index == items.lastIndex) {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ChoicesBottomSheetPrev() {
    ChoicesBottomSheet(
        title = "Teste",
        items = listOf("Teste")
    )
}
