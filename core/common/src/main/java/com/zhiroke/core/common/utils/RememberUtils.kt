package com.zhiroke.core.common.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
inline fun <T> rememberLambda(lambda: T) = remember { lambda }