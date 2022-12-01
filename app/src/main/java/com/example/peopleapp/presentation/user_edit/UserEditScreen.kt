package com.example.peopleapp.presentation.user_edit

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.peopleapp.presentation.user_edit.components.UserInputText
import kotlinx.coroutines.flow.collectLatest

@Composable
fun UserEditScreen(
    navController: NavController,
    viewModel: UserEditViewModel = hiltViewModel()
) {
    val userState = viewModel.state.value

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UserEditViewModel.UiEvent.SaveUser -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            EditTopBar(
                topAppBarText = "Add user"
            )
        },
        content = {
            EditContent(
                name = userState.name,
                lastName = userState.lastName,
                age = userState.age,
                onEvent = { viewModel.onEvent(it) }
            )
        },
        bottomBar = {
            EditBottomBar(
                onInsertUser = { viewModel.onEvent(UserEditEvent.InsertUser) }
            )
        }
    )
}

@Composable
fun EditTopBar(topAppBarText: String) {
    TopAppBar(
        title = {
            Text(
                text = topAppBarText,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        },
        backgroundColor = MaterialTheme.colors.surface
    )
}

@Composable
fun EditContent(
    name: String,
    lastName: String,
    age: String,
    onEvent: (UserEditEvent) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(44.dp))
        UserInputText(
            text = name,
            hint = "Name",
            onTextChange = { onEvent(UserEditEvent.EnteredName(it)) }
        )
        UserInputText(
            text = lastName,
            hint = "Last name",
            onTextChange = { onEvent(UserEditEvent.EnteredLastName(it)) }
        )
        UserInputText(
            text = age,
            hint = "Age",
            onTextChange = { onEvent(UserEditEvent.EnteredAge(it)) }
        )
    }
}

@Composable
fun EditBottomBar(
    modifier: Modifier = Modifier,
    onInsertUser: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 14.dp),
        onClick = { onInsertUser() }
    ) {
        Text(text = "Add user")
    }
}