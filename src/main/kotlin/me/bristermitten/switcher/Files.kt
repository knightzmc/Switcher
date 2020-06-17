package me.bristermitten.switcher

import java.io.File

infix fun File.copyAllTo(to: File): Int
{
    return copyAllUnless(to) {
        false
    }
}

fun File.copyAllUnless(to: File, excludeIf: (File) -> Boolean): Int
{
    return copyAllUnless(this, to = to, excludeIf = excludeIf)
}

private fun copyAllUnless(from: File, to: File, baseDir: File = from, excludeIf: (File) -> Boolean): Int
{
    var copiedCounter = 0
    val files = from.listFiles()
    if (files != null)
    {
        files.forEach {
            copiedCounter += copyAllUnless(
                from = it,
                to = to,
                baseDir = baseDir,
                excludeIf = excludeIf
            )
        }
    } else
    {
        if (!excludeIf(from))
        {
            val relative = from.relativeTo(baseDir)
            from.copyTo(to.resolve(relative), true)
            copiedCounter++
        }
    }

    return copiedCounter
}
