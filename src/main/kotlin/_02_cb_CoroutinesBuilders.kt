import kotlinx.coroutines.*

suspend fun coroutinesBuilders() {
    runBlockingDemo()
    coroutineScopeDemo()
    withContextDemo()
    GlobalScope.launch {

    }
}

// blocks the running thread
private fun runBlockingDemo() = runBlocking {
    val job = launch {
        delay(1000)
        println("runBlockingDemo delay")
    }
    job.join()
}

// suspend and release the thread
private suspend fun coroutineScopeDemo() = coroutineScope {
    val job = launch(start = CoroutineStart.LAZY) {
        delay(1000)
        println("coroutineScopeDemo delay")
    }
    job.start()
}

private suspend fun withContextDemo() = withContext(Dispatchers.Main) {
    async(start = CoroutineStart.LAZY) {

    }.await()
}

