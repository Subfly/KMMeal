package dev.subfly.kmmeal.android.common.components.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun BaseScreenLayout(
    screenModifier: Modifier = Modifier,
    topAppBar: @Composable () -> Unit,
    onSuccessLayout: @Composable () -> Unit,
    fab: (@Composable () -> Unit)? = null,
    isLoading: Boolean,
    errorMessage: String,
    onErrorRetry: () -> Unit = {}
) {
    Scaffold(
        modifier = screenModifier,
        topBar = topAppBar,
        floatingActionButton = {
            fab?.invoke()
        }
    ) { paddings ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddings),
            contentAlignment = Alignment.Center
        ) {
            when {
                // Lazy column in order to make refresh work :(
                errorMessage.isNotEmpty() -> Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 64.dp),
                        text = errorMessage,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.padding(top = 16.dp))
                    Button(
                        onClick = onErrorRetry
                    ) {
                        Text(text = "Retry")
                    }
                }
                isLoading -> CircularProgressIndicator()
                else -> onSuccessLayout()
            }
        }
    }
}