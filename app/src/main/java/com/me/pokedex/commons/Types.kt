package com.me.pokedex.commons

import androidx.compose.runtime.Composable
import com.me.pokedex.networking.ApiResult

typealias onDismissType = () -> Unit
typealias onDismissComposableType = @Composable () -> Unit
typealias callbackFlowApi = kotlinx.coroutines.flow.Flow<ApiResult<Any>>
typealias onDismissTypeSuspend = suspend () -> Unit
typealias topBarFun = @Composable (Int) -> Unit
@Composable
fun emptyComposable() {}