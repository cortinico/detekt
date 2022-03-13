package io.gitlab.arturbosch.detekt.formatting

import io.gitlab.arturbosch.detekt.formatting.wrappers.UnnecessaryParenthesesBeforeTrailingLambda
import io.gitlab.arturbosch.detekt.test.TestConfig
import io.gitlab.arturbosch.detekt.test.assertThat
import org.junit.jupiter.api.Test

/**
 * Test cases were used directly from KtLint to verify the wrapper rule:
 *
 * https://github.com/pinterest/ktlint/blob/master/ktlint-ruleset-experimental/src/test/kotlin/com/pinterest/ktlint/ruleset/experimental/UnnecessaryParenthesesBeforeTrailingLambdaRuleTest.kt
 */
class UnnecessaryParenthesesBeforeTrailingLambdaSpec {

    @Test
    fun `reports unnecessary parentheses in function call followed by lambda`() {
        val code =
            """
                fun countDash(input: String) =
                    "some-string".count() { it == '-' }
            """.trimIndent()
        val findings = UnnecessaryParenthesesBeforeTrailingLambda(TestConfig()).lint(code)
        assertThat(findings).hasSize(1)
    }
}
