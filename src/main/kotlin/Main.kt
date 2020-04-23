import com.github.ajalt.clikt.core.subcommands
import java.io.File

fun main(args: Array<String>)
{

    val switcher = Switcher()
    switcher
        .subcommands(
            Pull(switcher), Push(switcher)
        )
        .main(args)
}

infix fun File.copyAllTo(to: File)
{
    listFiles()?.forEach {
        it.copyRecursively(to.resolve(it.relativeTo(this)), true)
    }
}

fun File.copyAllUnless(to: File, excludeIf: (File) -> Boolean)
{
    listFiles()?.forEach {
        if (!excludeIf(it))
        {
            it.copyRecursively(to.resolve(it.relativeTo(this)), true)
        }
    }
}
