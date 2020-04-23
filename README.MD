# Switcher

Switcher is a tiny CLI used for quick switching between files.

The intended use case is easy switching between Minecraft server versions,
but it can also be used as a lightweight backup tool!

##How it works
Switcher uses and generates 2 main directories in the directory that it is executed in:
* `current` - This stores the current version of the files used. In the Minecraft use case, this
would be the current files of the server.
* `global` - This stores any files that should be kept constant between versions.
 For example, plugins that are not version independent so can be included in any version of the server.
 
 
##Commands
Switcher has 2 main commands:
* `pull` - This pulls a backup into the `current` directory. Switcher overwrites conflicting files,
but ignores files that are not present in the backup. The command flag `--override | -o` instructs 
Switcher to delete files that are not present for a "Hard Restore".
This command expects an argument of the backup directory to restore. For example: 
```
| current
| global
| 1.15
    | some-files
| 1.14
    | older-files
```
`./switcher pull 1.15` brings the files from the `1.15` directory and the `global` directory into the `current` directory


* `push` - This pushes the files in the `current` directory to a backup directory.
Switcher finds the version to back up by looking in 2 places:

1. The `current` directory, which will contain a `version` file if a loaded backup is present.
2. The command arguments, which may provide a backup directory.

Therefore, a `push` command will look similar to this:
`./switcher push` or `./switcher push 1.15` 

**Note that files from the `global` directory are not pushed**


##Building

Switcher uses Gradle, so building is as simple as running
`./gradlew build` on UNIX/Mac or `gradlew.bat build` on Windows.

Switcher also provides a script for using GraalVM to build a native image.
This requires Graal to be installed, and is as simple as running `./build-native.sh`
in the base directory.
