import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.suspendCancellableCoroutine
import java.io.Closeable
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

suspend fun continuation() = coroutineScope {

    val r1 = doHeavyDivision(9,3)
    val r2 = doHeavyDivision(r1,3)
    val r3 = doHeavyDivision(r2,3)
}


// region Problem
fun doHeavyDivision(
    x: Int,
    y: Int,
    onSuccess: (Int) -> Unit,
    onError: (Throwable) -> Unit,
) :Closeable? {
    Thread.sleep(200000)
    try {
        onSuccess(x / y)

    } catch (t: Throwable) {
        onError(t)
    }
    return  null
}
// endregion


// region Solution
suspend fun doHeavyDivision(
    x: Int, y: Int
) = suspendCancellableCoroutine <Int> { continuation ->
    val x = doHeavyDivision(x = x, y = y, onSuccess = {
        continuation.resume(it)
    }, onError = {
        continuation.resumeWithException(it)
    })
    continuation.invokeOnCancellation {
        x?.close()
    }
}
// endregion