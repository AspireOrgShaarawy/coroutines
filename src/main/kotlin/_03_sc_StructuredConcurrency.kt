import kotlinx.coroutines.*

@OptIn(ExperimentalStdlibApi::class)
suspend fun structuredConcurrency() = coroutineScope level0@{
    launch(Job()+Dispatchers.Default + CoroutineName("Level 1") + CoroutineExceptionHandler { coroutineContext, throwable ->  }) {
        printCoroutineContextEntries("Level 1")
        launch(Job()+Dispatchers.IO + CoroutineName("Level 2")) {
            printCoroutineContextEntries("Level 2")
            launch(Job()+CoroutineName("Level 3") ) {
                printCoroutineContextEntries("Level 3")
            }
        }
    }
}

@OptIn(ExperimentalStdlibApi::class)
suspend fun printCoroutineContextEntries(tag: String) = coroutineScope {
    ("####### $tag #######" +
            "\nName: ${this.coroutineContext[CoroutineName.Key]}" +
            "\nDispatcher: ${this.coroutineContext[CoroutineDispatcher.Key]}"+
            "\nError handler: ${this.coroutineContext[CoroutineExceptionHandler.Key]}")
        .also(::println)
}