package com.damikkg.kmmnewsapi.feature.base

import kotlinx.coroutines.CoroutineScope

expect open class SharedViewModel() {
    protected val sharedScope: CoroutineScope
    open fun onCleared()
}