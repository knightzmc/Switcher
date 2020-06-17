package me.bristermitten.switcher

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.file

class Pull(private val switcher: Switcher) : CliktCommand(name = "pull", help = "Load a version of the data")
{
    private val version by argument(name = "Version", help = "Version to pull").file(
        mustExist = true,
        canBeDir = true,
        canBeFile = false
    )
    private val override by option(
        "-o", "--override", "--overwrite",
        help = "If the previous version's files should be deleted"
    ).flag()

    override fun run()
    {
        if (override)
        {
            switcher.currentDir.listFiles()?.forEach {
                it.deleteRecursively()
            }
        }

        var counter = 0
        counter += version.copyAllUnless(switcher.currentDir) {
            it.name == "version"
        }

        counter += switcher.globalDir.copyAllTo(switcher.currentDir)

        switcher.currentVersion = version.name
        switcher.currentDir.resolve("version").writeText(version.name)

        println("${Color.GREEN}Successfully pulled version ${Color.MAGENTA}$version ${Color.YELLOW}(About $counter files changed)${Color.DEFAULT}")
    }
}
