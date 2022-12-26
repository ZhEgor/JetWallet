package com.zhiroke.core.components.pager

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.*
import com.zhiroke.core.theme.demensions.dp_2
import com.zhiroke.core.theme.utils.MaterialColor


@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalPagerWithTransition(
    modifier: Modifier = Modifier,
    count: Int,
    content: @Composable PagerScope.(page: Int) -> Unit
) {

    val pagerState = rememberPagerState()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        HorizontalPager(
            modifier = modifier,
            count = count,
            state = pagerState,
        ) { page ->

            PagerItemWithTransition(page = page) {

                content.invoke(this@HorizontalPager, page)
            }
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            activeColor = MaterialColor.primary,
            inactiveColor = MaterialColor.primaryContainer,
            indicatorShape = RoundedCornerShape(dp_2)
        )
    }
}