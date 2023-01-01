import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

val options = mapOf(
    "cb" to ::coroutinesBuilders,
    "sc" to ::structuredConcurrency,
    "jt" to ::jobTypes,
    "co" to ::continuation,
)

suspend fun main(args: Array<String>) {
    args.firstOrNull()?.also {
        if (options.containsKey(it))
            options[it]!!()
        else
            println("Select one of these options: ${options.keys}")
    }
}