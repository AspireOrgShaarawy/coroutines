import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

suspend fun temp() = coroutineScope{
    printCoroutineContextEntries("L0")
    launch(Dispatchers.IO + CoroutineName("level 1")) level1@{
        printCoroutineContextEntries("L1")
        launch(CoroutineName("Level 2") + Dispatchers.Unconfined) level2@{
            printCoroutineContextEntries("L2")
            launch(CoroutineName("Level 3")) level3@{
                printCoroutineContextEntries("L2")
            }
        }
    }
}