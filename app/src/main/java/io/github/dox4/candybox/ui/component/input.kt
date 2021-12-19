package io.github.dox4.candybox.ui.component

//
//@Composable
//fun SimpleTextInput(
//    property: Property,
//    focusRequester: FocusRequester,
//    viewModel: SavedViewModel,
//    imeAction: ImeAction,
//    keyboardActions: KeyboardActions
//) {
//    var value by rememberSaveable {
//        mutableStateOf("")
//    }
//    OutlinedTextField(
//        value = value,
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 15.dp)
//            .focusRequester(focusRequester = focusRequester),
//        placeholder = { Text(text = property.hint) },
//        label = { Text(text = property.label) },
//        onValueChange = {
//            viewModel.update(property.id, it)
//            value = it
//        },
//        keyboardOptions = KeyboardOptions(
//            imeAction = imeAction,
//        ),
//        keyboardActions = keyboardActions
//    )
//}
