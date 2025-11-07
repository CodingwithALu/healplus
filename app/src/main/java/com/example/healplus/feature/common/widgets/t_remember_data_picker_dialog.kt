package com.example.healplus.feature.common.widgets

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar

@Composable
fun rememberDatePickerDialog(
        context: Context = LocalContext.current,
        calendar: Calendar = Calendar.getInstance(),
        onDateSelected: (String) -> Unit
): DatePickerDialog {
    return remember {
        DatePickerDialog(
                context,
                { _, year, month, dayOfMonth -> onDateSelected("$dayOfMonth/${month + 1}/$year") },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        )
    }
}
