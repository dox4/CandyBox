package io.github.dox4.candybox.ui.component

//@ExperimentalComposeUiApi
//@Composable
//fun PropertyFields(list: List<Property>, viewModel: SavedViewModel) {
//    val focusManager = LocalFocusManager.current
//    val focusRequester = remember {
//        FocusRequester()
//    }
//
//    LazyColumn(
//        modifier = Modifier
//            .fillMaxWidth(),
//        verticalArrangement = Arrangement.spacedBy(5.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        itemsIndexed(list) { idx, property ->
//            val isLast = idx == list.lastIndex
//            val imeAction =
//                if (isLast) ImeAction.Done else ImeAction.Next
//            val keyboardActions =
//                if (isLast) KeyboardActions(
//                    onDone = {
//                        focusManager.clearFocus()
//                    })
//                else KeyboardActions(
//                    onNext = {
//                        focusManager.moveFocus(FocusDirection.Down)
//                    })
//
//            SimpleTextInput(
//                property = property,
//                focusRequester = focusRequester,
//                viewModel = viewModel,
//                imeAction = imeAction,
//                keyboardActions = keyboardActions
//            )
//
//        }
//        item {
//            Button(
//                shape = MaterialTheme.shapes.large,
//                modifier = Modifier
//                    .padding(top = 12.dp),
//                onClick = {
//                }) {
//                Text(text = "Save", fontSize = 16.sp)
//            }
//        }
//    }
//}
