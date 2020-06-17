package me.bristermitten.switcher

import com.github.ajalt.clikt.core.CliktCommand
import java.io.File

class Switcher : CliktCommand()
{
    val currentDir = File("current")

    private val versionFile = currentDir.resolve("version")
    var currentVersion = if (versionFile.exists()) versionFile.readText() else null

    val globalDir = File("global")

    init
    {
        currentDir.mkdir()
        globalDir.mkdir()
    }

    override fun run() = Unit
}
