package me.bristermitten.switcher

enum class Color(private val foregroundCode: Int)
{
    BLACK(30),
    RED(31),
    GREEN(32),
    YELLOW(33),
    BLUE(34),
    MAGENTA(35),
    CYAN(36),
    WHITE(37),
    DEFAULT(39);

    fun toForegroundString() = toString(foregroundCode)
    fun toBackgroundString() = toString(foregroundCode + 10)

    private fun toString(code: Int): String
    {
        return "${27.toChar()}[${code}m"
    }

    override fun toString(): String
    {
        return toForegroundString()
    }
}
