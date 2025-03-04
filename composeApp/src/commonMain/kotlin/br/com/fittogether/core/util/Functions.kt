package br.com.fittogether.core.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import br.com.fittogether.domain.model.user.User
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun phoneFilter(text: AnnotatedString): TransformedText {
    val cleanedText = text.text.replace(Regex("[^\\d]"), "")

    val formattedText = buildString {
        cleanedText.take(2).chunked(1).forEach { append(it) }
        if (cleanedText.length > 2) append(" ")
        cleanedText.drop(2).take(1).forEach { append(it) }
        if (cleanedText.length > 3) append(" ")
        cleanedText.drop(3).take(4).forEach { append(it) }
        if (cleanedText.length > 7) append("-")
        cleanedText.drop(7).take(4).forEach { append(it) }
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return when {
                offset <= 2 -> offset
                offset <= 3 -> offset + 1
                offset <= 7 -> offset + 2
                else -> offset + 3
            }.coerceIn(0, formattedText.length)
        }

        override fun transformedToOriginal(offset: Int): Int {
            val originalOffset = when {
                offset <= 2 -> offset
                offset <= 4 -> offset - 1
                offset <= 8 -> offset - 2
                else -> offset - 3
            }.coerceIn(0, cleanedText.length)

            return originalOffset.coerceIn(0, cleanedText.length)
        }
    }

    return TransformedText(AnnotatedString(formattedText), numberOffsetTranslator)
}

fun String.keepOnlyNumbers(): String {
    return this.replace(Regex("[^\\d]"), "")
}

fun String.formatPhone() : String {
    var phone = ""
    this.forEachIndexed { index, c ->
        if (index > 2) {
            if (index == 3) {
                phone += "("
            }

            if (index == 5) {
                phone += ") "
            }

            if (index == 6) {
                phone += " "
            }

            if (index == 10) {
                phone += " - "
            }

            phone += c
        }
    }

    return phone
}

inline fun <reified D> D.toJson() : String {
    return Json.encodeToString(this)
}

inline fun <reified D> String.toObj() : D? {
    return Json.decodeFromString(this)
}

class DateVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.text.length >= 8) text.text.substring(0..7) else text.text
        var output = ""
        for (i in trimmed.indices) {
            output += trimmed[i]
            if (i < 4 && i % 2 == 1) output += "/"
        }
        val dateTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 1) return offset
                if (offset <= 3) return offset + 1
                if (offset <= 7) return offset + 2
                return 10
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 1) return offset
                if (offset <= 4) return offset - 1
                if (offset <= 9) return offset - 2
                return 8
            }

        }

        return TransformedText(
            AnnotatedString(output),
            dateTranslator
        )
    }
}

fun formatDateWithSlashes(date: String): String {
    if (date.length != 8) {
        throw IllegalArgumentException("A data deve ter exatamente 8 caracteres.")
    }

    return "${date.substring(0, 2)}/${date.substring(2, 4)}/${date.substring(4, 8)}"
}