package com.example.test


import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.example.test.testValidations.ExpenseValidator.validateExpenseInput
import com.example.test.testValidations.ExpenseValidator
import com.google.common.truth.Truth.assertThat


@RunWith(JUnit4::class)
class ExpenseValidatorTest{

    @Test
    fun whenExpenseInputIsValid() {
        val expense = 150
        val expenseCategory = "Transport"
        val result = validateExpenseInput(expense,expenseCategory)
        assertThat(result).isEqualTo(true)
    }

    @Test
    fun whenExpenseInputIsInValid() {
        val expense = 0
        val expenseCategory = ""
        val result = validateExpenseInput(expense, expenseCategory)
        assertThat(result).isEqualTo(false)
    }

}