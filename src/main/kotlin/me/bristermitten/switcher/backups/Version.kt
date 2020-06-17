package me.bristermitten.switcher.backups

sealed class Version(val versionIdentifier: String) : Comparable<Version>
{
    abstract fun matches(format: String): Boolean
}

class StandardVersion(val major: Int, val minor: Int, val patch: Int = 0) : Version("$major.$minor.$patch")
{
    private val regex = Regex("[0-9]+.[0-9]+(.[0-9]+)?")

    override fun matches(format: String): Boolean
    {
        return format.matches(regex)
    }

    override fun compareTo(other: Version): Int
    {
        if (other is StandardVersion)
        {
            return when
            {
                other.major > this.major -> -1
                other.minor > this.minor -> -1
                other.patch > this.patch -> -1
                other.major == this.major && other.minor == this.minor && other.patch == this.patch -> 0
                else -> 1
            }
        }
        return versionIdentifier.compareTo(other.versionIdentifier)
    }
}
