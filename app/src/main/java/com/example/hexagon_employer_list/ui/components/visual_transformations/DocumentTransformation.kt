package com.example.hexagon_employer_list.ui.components.visual_transformations

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import kotlin.math.absoluteValue

class DocumentTransformation : VisualTransformation {

    companion object {
        const val CPF_MASK = "###.###.###-##"
        const val CNPJ_MASK = "##.###.###/####-##"
    }

    var mask = CPF_MASK

    override fun filter(text: AnnotatedString): TransformedText {
        if (text.length > 11) {
            mask = CNPJ_MASK
        }
        var out = ""
        var maskIndex = 0
        text.forEach { char ->
            while (mask.indices.filter { mask[it] != '#' }.contains(maskIndex)) {
                out += mask[maskIndex]
                maskIndex++
            }
            out += char
            maskIndex++
        }
        return TransformedText(AnnotatedString(out), offsetTranslator())
    }

    private fun offsetTranslator() = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            val offsetValue = offset.absoluteValue
            if (offsetValue == 0) return 0
            var numberOfHashtags = 0
            val masked = mask.takeWhile {
                if (it == '#') numberOfHashtags++
                numberOfHashtags < offsetValue
            }
            return masked.length + 1
        }

        override fun transformedToOriginal(offset: Int): Int {
            return mask.take(offset.absoluteValue).count { it == '#' }
        }
    }
}