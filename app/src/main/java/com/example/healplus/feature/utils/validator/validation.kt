package com.example.healplus.feature.utils.validator
import android.util.Patterns
import java.util.regex.Pattern

object ValidationUtils {

    /**
     * Empty Text Validation
     */
    fun validateEmptyText(fieldName: String, value: String?): String? {
        return if (value.isNullOrEmpty()) {
            "$fieldName is required"
        } else null
    }

    /**
     * Email Validation
     */
    fun validateEmail(value: String?): String? {
        if (value.isNullOrEmpty()) {
            return "Email is required."
        }

        // Using Android's built-in email pattern or custom regex
        val emailRegex = Pattern.compile("^[\\w\\-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")

        return if (!emailRegex.matcher(value).matches()) {
            "Invalid email address."
        } else null
    }

    /**
     * Alternative email validation using Android's Patterns
     */
    fun validateEmailWithPatterns(value: String?): String? {
        if (value.isNullOrEmpty()) {
            return "Email is required."
        }

        return if (!Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            "Invalid email address."
        } else null
    }

    /**
     * Password Validation
     */
    fun validatePassword(value: String?): String? {
        if (value.isNullOrEmpty()) {
            return "Password is required."
        }

        // Check for minimum password length
        if (value.length < 6) {
            return "Password must be at least 6 characters long."
        }

        // Check for uppercase letters
        if (!value.contains(Regex("[A-Z]"))) {
            return "Password must contain at least one uppercase letter."
        }

        // Check for numbers
        if (!value.contains(Regex("[0-9]"))) {
            return "Password must contain at least one number."
        }

        // Check for special characters
        if (!value.contains(Regex("[!@#\$%^&*(),.?\":{}|<>]"))) {
            return "Password must contain at least one special character."
        }

        return null
    }

    /**
     * Simple Password Validation (chỉ kiểm tra độ dài tối thiểu)
     */
    fun validateSimplePassword(value: String?): String? {
        if (value.isNullOrEmpty()) {
            return "Password is required."
        }

        return if (value.length < 6) {
            "Password must be at least 6 characters long."
        } else null
    }

    /**
     * Phone Number Validation
     */
    fun validatePhoneNumber(value: String?): String? {
        if (value.isNullOrEmpty()) {
            return "Phone number is required."
        }

        // Regular expression for phone number validation (10-digit format)
        val phoneRegex = Regex("^\\d{10}$")

        return if (!phoneRegex.matches(value)) {
            "Invalid phone number format (10 digits required)."
        } else null
    }

    /**
     * Vietnamese Phone Number Validation
     */
    fun validateVietnamesePhoneNumber(value: String?): String? {
        if (value.isNullOrEmpty()) {
            return "Số điện thoại là bắt buộc."
        }

        // Vietnamese phone number patterns
        val phoneRegex = Regex("^(\\+84|84|0)(3[2-9]|5[689]|7[06-9]|8[1-689]|9[0-46-9])[0-9]{7}$")

        return if (!phoneRegex.matches(value)) {
            "Số điện thoại không hợp lệ."
        } else null
    }

    /**
     * Name Validation
     */
    fun validateName(value: String?): String? {
        if (value.isNullOrEmpty()) {
            return "Name is required."
        }

        if (value.length < 2) {
            return "Name must be at least 2 characters long."
        }

        // Check if name contains only letters and spaces
        val nameRegex = Regex("^[a-zA-ZÀ-ỹ\\s]+$")

        return if (!nameRegex.matches(value)) {
            "Name can only contain letters and spaces."
        } else null
    }

    /**
     * Confirm Password Validation
     */
    fun validateConfirmPassword(password: String?, confirmPassword: String?): String? {
        if (confirmPassword.isNullOrEmpty()) {
            return "Confirm password is required."
        }

        return if (password != confirmPassword) {
            "Passwords do not match."
        } else null
    }

    /**
     * Generic Length Validation
     */
    fun validateLength(value: String?, minLength: Int, maxLength: Int, fieldName: String): String? {
        if (value.isNullOrEmpty()) {
            return "$fieldName is required."
        }

        return when {
            value.length < minLength -> "$fieldName must be at least $minLength characters long."
            value.length > maxLength -> "$fieldName must not exceed $maxLength characters."
            else -> null
        }
    }

    /**
     * Credit Card Validation (basic Luhn algorithm)
     */
    fun validateCreditCard(value: String?): String? {
        if (value.isNullOrEmpty()) {
            return "Credit card number is required."
        }

        val cleanedNumber = value.replace("\\s".toRegex(), "")

        if (cleanedNumber.length < 13 || cleanedNumber.length > 19) {
            return "Invalid credit card number length."
        }

        if (!cleanedNumber.all { it.isDigit() }) {
            return "Credit card number can only contain digits."
        }

        // Luhn algorithm check
        if (!isValidLuhn(cleanedNumber)) {
            return "Invalid credit card number."
        }

        return null
    }

    /**
     * Luhn Algorithm for credit card validation
     */
    private fun isValidLuhn(number: String): Boolean {
        var sum = 0
        var alternate = false

        for (i in number.length - 1 downTo 0) {
            var digit = number[i].toString().toInt()

            if (alternate) {
                digit *= 2
                if (digit > 9) {
                    digit = digit % 10 + digit / 10
                }
            }

            sum += digit
            alternate = !alternate
        }

        return sum % 10 == 0
    }

    /**
     * URL Validation
     */
    fun validateUrl(value: String?): String? {
        if (value.isNullOrEmpty()) {
            return "URL is required."
        }

        return if (!Patterns.WEB_URL.matcher(value).matches()) {
            "Invalid URL format."
        } else null
    }

    /**
     * Age Validation
     */
    fun validateAge(value: String?): String? {
        if (value.isNullOrEmpty()) {
            return "Age is required."
        }

        val age = value.toIntOrNull()

        return when {
            age == null -> "Age must be a valid number."
            age < 13 -> "You must be at least 13 years old."
            age > 120 -> "Please enter a valid age."
            else -> null
        }
    }
}