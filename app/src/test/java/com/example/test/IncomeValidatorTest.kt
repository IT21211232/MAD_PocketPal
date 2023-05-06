package com.example.test


import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import com.example.test.testValidations.IncomeValidator.validateIncomeInput
import com.google.common.truth.Truth.assertThat


@RunWith(JUnit4::class)
class IncomeValidatorTest{

    @Test
    fun whenIncomeInputIsValid() {
        val income = 500
        val incomeCategory = "Interest Income"
        val result = validateIncomeInput(income, incomeCategory)
        assertThat(result).isEqualTo(true)
    }

    @Test
    fun whenIncomeInputIsInValid() {
        val income = 0
        val incomeCategory = ""
        val result = validateIncomeInput(income, incomeCategory)
        assertThat(result).isEqualTo(false)
    }

}