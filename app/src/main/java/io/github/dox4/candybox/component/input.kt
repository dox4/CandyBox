package io.github.dox4.candybox.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import io.github.dox4.candybox.entity.Property
import io.github.dox4.candybox.viewmodel.SavedViewModel

@Composable
fun SimpleTextInput(
    property: Property,
    focusRequester: FocusRequester,
    viewModel: SavedViewModel,
    imeAction: ImeAction,
    keyboardActions: KeyboardActions
) {
    var value by rememberSaveable {
        mutableStateOf("")
    }
    OutlinedTextField(
        value = value,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .focusRequester(focusRequester = focusRequester),
        placeholder = { Text(text = property.hint) },
        label = { Text(text = property.label) },
        onValueChange = {
            viewModel.update(property.id, it)
            value = it
        },
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction,
        ),
        keyboardActions = keyboardActions
    )
}
