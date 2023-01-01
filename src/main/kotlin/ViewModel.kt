import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

class ViewModel :CoroutineScope by ProcessScope(){

}

class ProcessScope:CoroutineScope{
    override val coroutineContext: CoroutineContext
        get() = EmptyCoroutineContext
    // free up resources

}