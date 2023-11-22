package org.example.logdemo.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.browser.api
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.forms.TextInput
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.dom.Text

@Page
@Composable
fun HomePage() {
    Column(Modifier.fillMaxSize().gap(2.cssRem).padding(top = 5.cssRem), horizontalAlignment = Alignment.CenterHorizontally) {
        var logText by remember { mutableStateOf("") }

        fun sendAndClearLogText() {
            val logTextBytes = logText.encodeToByteArray()
            logText = ""
            CoroutineScope(window.asCoroutineDispatcher()).launch {
                window.api.put("log", body = logTextBytes)
            }
        }

        TextInput(
            logText,
            onTextChanged = { logText = it },
            placeholder = "Log to server",
            onCommit = { sendAndClearLogText() }
        )
        Button(onClick = { sendAndClearLogText() }) {
            Text("Send")
        }
    }
}
