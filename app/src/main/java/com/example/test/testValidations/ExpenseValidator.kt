package com.example.test.testValidations

object ExpenseValidator{
    fun validateExpenseInput(expense: Int, expenseCategory: String): Boolean {
        return !(expense <= 0 || expenseCategory.isEmpty())
    }
}