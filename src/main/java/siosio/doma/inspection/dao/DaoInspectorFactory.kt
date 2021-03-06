package siosio.doma.inspection.dao

import com.intellij.codeInsight.daemon.impl.quickfix.*
import com.intellij.psi.*
import siosio.doma.extension.*
import siosio.doma.psi.*

val parameterTypeCheck: ParameterRule.() -> Unit = {
    message = "inspection.dao.entity-param-not-found"
    rule = { dao ->
        when {
            dao.useSqlFile() -> true
            else ->
                when (size) {
                    1 -> first().isEntity()
                    else -> false
                }
        }
    }
}

val deleteMethodRule =
        rule {
            sql(false)

            // 引数チェック
            parameterRule(parameterTypeCheck)
        }

val batchUpdateMethodRule =
        rule {
            sql(false)
        }

val batchDeleteMethodRule =
        rule {
            sql(false)
        }

val scriptMethodRule =
        rule {
            sql(true)
        }
