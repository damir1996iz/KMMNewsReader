package com.damikkg.kmmnewsapi.feature.base

import kotlinx.coroutines.*
import platform.darwin.DISPATCH_TIME_NOW
import platform.darwin.NSEC_PER_MSEC
import platform.darwin.dispatch_after
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import platform.darwin.dispatch_time
import kotlin.coroutines.CoroutineContext
import kotlin.native.internal.GC

actual open class SharedViewModel actual constructor() {
    protected actual val sharedScope: CoroutineScope = createViewModelScope()

    actual open fun onCleared() {
        sharedScope.cancel()

        dispatch_async(dispatch_get_main_queue()) { GC.collect() }
    }
}

@ThreadLocal
private var createViewModelScope: () -> CoroutineScope = {
    CoroutineScope(createUIDispatcher())
}

private fun createUIDispatcher(): CoroutineDispatcher = UIDispatcher()

@OptIn(ExperimentalUnsignedTypes::class, InternalCoroutinesApi::class)
internal class UIDispatcher : CoroutineDispatcher(), Delay {
    private val mQueue = dispatch_get_main_queue()

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        dispatch_async(mQueue) {
            block.run()
        }
    }

    override fun scheduleResumeAfterDelay(
        timeMillis: Long,
        continuation: CancellableContinuation<Unit>
    ) {
        dispatch_after(
            `when` = dispatch_time(
                DISPATCH_TIME_NOW,
                timeMillis * NSEC_PER_MSEC.toLong()
            ),
            queue = mQueue
        ) {
            val result = continuation.tryResume(Unit)
            if (result != null) {
                continuation.completeResume(result)
            }
        }
    }

    override fun invokeOnTimeout(
        timeMillis: Long,
        block: Runnable,
        context: CoroutineContext
    ): DisposableHandle {
        var disposed = false
        dispatch_after(
            `when` = dispatch_time(
                DISPATCH_TIME_NOW,
                timeMillis * NSEC_PER_MSEC.toLong()
            ),
            queue = mQueue
        ) {
            if (disposed) return@dispatch_after

            block.run()
        }
        return DisposableHandle { disposed = true }
    }
}