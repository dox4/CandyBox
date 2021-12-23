package io.github.dox4.candybox.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import io.github.dox4.candybox.data.vm.BookOrWorldViewModel
import io.github.dox4.candybox.domain.BookOrWorldType
import io.github.dox4.candybox.ui.component.TopAppBarText
import io.github.dox4.candybox.ui.nav.Screen
import io.github.dox4.candybox.util.HOME_TAB_TITLES

@Composable
fun AddBookOrWorld(
    navController: NavController,
    index: Int,
    vm: BookOrWorldViewModel = hiltViewModel()
) {
    vm.type = if (index == 0) BookOrWorldType.BOOK else BookOrWorldType.WORLD
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TopAppBarText(
                        text = "新增 " + HOME_TAB_TITLES[index]
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "后退")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                vm.save()
                navController.popBackStack()
            }) {
                Icon(imageVector = Icons.Filled.Save, contentDescription = "保存")
            }
        }
    ) {
        val focusManager = LocalFocusManager.current
        Column {

            OutlinedTextField(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 3.dp)
                    .fillMaxWidth(),
                value = vm.name.value,
                onValueChange = {
                    vm.updateName(it)
                },
                label = { Text("名称") },
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                textStyle = MaterialTheme.typography.h5
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
            )
            OutlinedTextField(
                modifier = Modifier
                    .padding(8.dp, 3.dp)
                    .fillMaxSize(),
                value = vm.desc.value,
                onValueChange = {
                    vm.updateDesc(it)
                },
                label = { Text("简介") },
                textStyle = MaterialTheme.typography.body1
            )

        }
    }
}