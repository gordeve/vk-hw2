package com.gordeve.hw2.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gordeve.hw2.R

@Composable
fun ErrorScreen(exception: Exception, action: () -> Unit, modifier: Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize().padding(15.dp)
    ) {
        Icon(
            Icons.Rounded.Warning,
            contentDescription = stringResource(R.string.error),
            tint = MaterialTheme.colorScheme.error
        )
        Text(
            stringResource(R.string.error),
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )
        Text(
            exception.message ?: "",
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
        Button(
            onClick=action,
            colors = buttonColors(containerColor = MaterialTheme.colorScheme.errorContainer)
        ) {
            Text(stringResource(R.string.retry), color = MaterialTheme.colorScheme.onErrorContainer)
        }
    }
}