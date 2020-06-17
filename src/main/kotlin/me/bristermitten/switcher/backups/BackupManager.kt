package me.bristermitten.switcher.backups

import java.io.File

class BackupManager
{
    private val backups = mutableSetOf<Backup>()

    fun loadAll(baseDir: File)
    {
        val files = baseDir.listFiles() ?: return

        val backupTypes = Backup::class.sealedSubclasses
        files.filter(File::isDirectory)
            .forEach {

            }
    }

    fun all() = backups.toSet()
}
