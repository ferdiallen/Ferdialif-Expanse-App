package main.core.util

enum class PlatformIdentifier {
    Desktop,
    Web
}

expect fun platformIdentifier() : PlatformIdentifier