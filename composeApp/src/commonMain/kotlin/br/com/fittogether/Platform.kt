package br.com.fittogether

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform