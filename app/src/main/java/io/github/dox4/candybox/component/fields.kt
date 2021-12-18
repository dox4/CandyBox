package io.github.dox4.candybox.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.dox4.candybox.entity.Property
import io.github.dox4.candybox.viewmodel.SavedViewModel

@ExperimentalComposeUiApi
@Composable
fun PropertyFields(list: List<Property>, viewModel: SavedViewModel) {
    val focusManager = LocalFocusManager.current
    val focusRequester = remember {
        FocusRequester()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        itemsIndexed(list) { idx, property ->
            val isLast = idx == list.lastIndex
            val imeAction =
                if (isLast) ImeAction.Done else ImeAction.Next
            val keyboardActions =
                if (isLast) KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    })
                else KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    })

            SimpleTextInput(
                property = property,
                focusRequester = focusRequester,
                viewModel = viewModel,
                imeAction = imeAction,
                keyboardActions = keyboardActions
            )

        }
        item {
            Button(
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .padding(top = 12.dp),
                onClick = {
                }) {
                Text(text = "Save", fontSize = 16.sp)
            }
        }
    }
}
