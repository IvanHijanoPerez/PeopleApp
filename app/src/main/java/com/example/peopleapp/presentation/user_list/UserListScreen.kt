package com.example.peopleapp.presentation.user_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.peopleapp.domain.model.User
import com.example.peopleapp.presentation.Screen
import com.example.peopleapp.presentation.user_list.components.UserItem

@Composable
fun UserListScreen(
    navController: NavController,
    viewModel: UserListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Scaffold(
        topBar = {
            TopBar()
        },
        floatingActionButton = {
            AddUser(
                onAddClicked = { navController.navigate(Screen.UserEditScreen.route) }
            )
        },
        content = { innerPadding ->
            ContentPage(
                modifier = Modifier.padding(innerPadding),
                onDeleteUser = {
                    viewModel.onEvent(UserListEvent.DeleteUser(it))
                },
                onEditUser = {
                    navController.navigate(Screen.UserEditScreen.passId(it))
                },
                users = state.users
            )
        }
    )
}

@Composable
fun TopBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = "Users",
                textAlign = TextAlign.Center,
                modifier = modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        },
        backgroundColor = MaterialTheme.colors.surface
    )
}

@Composable
fun ContentPage(
    modifier: Modifier = Modifier,
    onDeleteUser: (user: User) -> Unit,
    onEditUser: (id: Int?) -> Unit,
    users: List<User> = emptyList()
) {
    Surface(
        color = MaterialTheme.colors.surface,
        modifier = modifier
    ) {
        LazyColumn {
            items(users) { user ->
                UserItem(
                    user = user,
                    onEditUser = { onEditUser(user.id) },
                    onDeleteUser = { onDeleteUser(user) }
                )
            }
        }
    }
}


@Composable
fun AddUser(
    modifier: Modifier = Modifier,
    onAddClicked: () -> Unit
) {
    FloatingActionButton(
        onClick = onAddClicked,
        modifier = modifier
            .height(52.dp)
            .widthIn(min = 52.dp),
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Icon(imageVector = Icons.Outlined.Add, contentDescription = "Add user")

    }

}
