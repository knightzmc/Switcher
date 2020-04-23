import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.optional
import java.io.File

class Push(private val switcher: Switcher) :
    CliktCommand(name = "push", help = "Save the current version of the data")
{
    private val destination by argument(
        "destination",
        "The destination directory. If not specified, the current option will be used"
    ).optional()

    override fun run()
    {
        val currentVersion = switcher.currentVersion ?: destination
        if (currentVersion == null)
        {
            println(
                """
                ${Color.RED}Error: No current version defined.
                Either create a file named ${Color.MAGENTA}version ${Color.RED}with the current version,
                or specify a version in the command${Color.DEFAULT}
                """.trimIndent()
            )
            return
        }
        val versionDirectory = File(currentVersion)

        switcher.currentDir.copyAllUnless(versionDirectory) {
            val relativeInGlobalDirectory = switcher.globalDir.resolve(it.relativeTo(it.parentFile))
            it.name == "version" || relativeInGlobalDirectory.exists()
        }

        println("${Color.GREEN}Successfully pushed version $currentVersion")
    }
}
