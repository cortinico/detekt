package io.gitlab.arturbosch.detekt.formatting

import com.pinterest.ktlint.core.Rule.VisitorModifier.RunAsLateAsPossible
import com.pinterest.ktlint.core.Rule.VisitorModifier.RunOnRootNodeOnly
import io.github.detekt.test.utils.compileContentForTest
import io.gitlab.arturbosch.detekt.api.Config
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class KtLintMultiRuleSpec {

    @Nested
    inner class `KtLintMultiRule rule` {

        @Test
        fun `sorts rules correctly`() {
            val ktlintRule = KtLintMultiRule(Config.empty)
            ktlintRule.visitFile(compileContentForTest(""))
            val sortedRules = ktlintRule.getSortedRules()
            assertThat(sortedRules).isNotEmpty

            assertThat(sortedRules.indexOfFirst { RunOnRootNodeOnly in it.wrapping.visitorModifiers })
                .isGreaterThan(-1)
                .isLessThan(sortedRules.indexOfFirst { RunOnRootNodeOnly !in it.wrapping.visitorModifiers })

            assertThat(sortedRules.indexOfFirst { RunOnRootNodeOnly !in it.wrapping.visitorModifiers })
                .isGreaterThan(-1)
                .isLessThan(sortedRules.indexOfFirst { RunOnRootNodeOnly in it.wrapping.visitorModifiers && RunAsLateAsPossible in it.wrapping.visitorModifiers })

            assertThat(sortedRules.indexOfFirst { RunOnRootNodeOnly in it.wrapping.visitorModifiers && RunAsLateAsPossible in it.wrapping.visitorModifiers })
                .isGreaterThan(-1)
                .isLessThan(sortedRules.indexOfFirst { RunOnRootNodeOnly !in it.wrapping.visitorModifiers && RunAsLateAsPossible in it.wrapping.visitorModifiers })
        }
    }
}
