import kotlinx.coroutines.*

suspend fun jobTypes() {
    val scope = CoroutineScope(SupervisorJob())


    scope.launch(SupervisorJob()) {
        launch() {
            delay(2000)
            println("Job 1")
            throw RuntimeException("### Forced run time error ###")
        }
        launch {
            delay(4000)
            println("Job 2")
        }
        launch() {
            delay(6000)
            println("Job 3")
        }

    }

    delay(7000)
}