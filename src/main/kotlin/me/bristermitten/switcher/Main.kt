package me.bristermitten.switcher

import com.github.ajalt.clikt.core.subcommands
import me.bristermitten.switcher.Pull
import me.bristermitten.switcher.Push

fun main(args: Array<String>)
{

    val switcher = Switcher()
    switcher
        .subcommands(
            Pull(switcher), Push(switcher)
        )
        .main(args)
}
