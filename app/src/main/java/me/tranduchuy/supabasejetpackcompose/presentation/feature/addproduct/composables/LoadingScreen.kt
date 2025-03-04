package me.tranduchuy.supabasejetpackcompose.presentation.feature.addproduct.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier,
    message: String,
    onCancelSelected: () -> Unit,
) {
    Column(
        modifier = modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        CircularProgressIndicator(modifier = modifier.size(64.dp))
        Text(text = message,
            style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = modifier.weight(1f))
        OutlinedButton(
            modifier = modifier
                .fillMaxWidth(),
            onClick = onCancelSelected
        ) {
            Text(text = "Cancel")
        }
    }

}