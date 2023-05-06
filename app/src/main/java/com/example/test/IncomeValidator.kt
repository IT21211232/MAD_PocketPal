package com.example.test

object IncomeValidator {
    fun validateIncomeInput(income: Int, incomeCategory: String): Boolean {
        return !(income <= 0 || incomeCategory.isEmpty())
    }
}