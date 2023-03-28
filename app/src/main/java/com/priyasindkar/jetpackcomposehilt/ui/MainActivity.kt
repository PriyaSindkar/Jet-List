package com.priyasindkar.jetpackcomposehilt.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.priyasindkar.jetpackcomposehilt.R
import com.priyasindkar.jetpackcomposehilt.data.Names
import com.priyasindkar.jetpackcomposehilt.ui.theme.JetpackComposeWithHiltTheme
import com.priyasindkar.jetpackcomposehilt.ui.theme.Purple80
import com.priyasindkar.jetpackcomposehilt.ui.theme.PurpleGrey80
import com.priyasindkar.jetpackcomposehilt.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainActivityViewModel: MainActivityViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JetpackComposeWithHiltTheme(dynamicColor = false) {
                Scaffold(
                    topBar = {
                        SmallTopAppBar(
                            title = {
                                Text(
                                    stringResource(id = R.string.app_name),
                                )
                            },
                            navigationIcon = {
                                IconButton(
                                    onClick = { finish() },
                                ) {
                                    Icon(
                                        Icons.Filled.ArrowBack,
                                        tint = MaterialTheme.colorScheme.onPrimary,
                                        contentDescription = "Back"
                                    )
                                }
                            },
                            colors = TopAppBarDefaults.mediumTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                titleContentColor = MaterialTheme.colorScheme.onPrimary
                            )
                        )
                    }) { contentPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(contentPadding),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Content(mainActivityViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun Content(viewModel: MainActivityViewModel) {

    val listOfNames by viewModel.listOfNames.collectAsState(initial = emptyList())
    var nameValue by remember { mutableStateOf(TextFieldValue()) }
    var isNameTextError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Bottom
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .weight(0.7f),
            verticalArrangement = Arrangement.Top
        ) {
            items(listOfNames, itemContent = {
                ListItem(it)
            })
        }
        Spacer(modifier = Modifier.size(8.dp))
        Column(
            modifier = Modifier
                .background(color = PurpleGrey80)
                .wrapContentHeight()
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .wrapContentSize(),
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .wrapContentSize()
                        .weight(0.6f)
                        .padding(top = 16.dp, bottom = if (isNameTextError) 0.dp else 16.dp)
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(32.dp)
                        ),
                    value = nameValue,
                    shape = RoundedCornerShape(32.dp),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Send,
                        keyboardType = KeyboardType.Text
                    ),
                    isError = isNameTextError,
                    maxLines = 1,
                    onValueChange = {
                        isNameTextError = it.text.isEmpty()
                        nameValue = it
                    }
                )

                TextButton(
                    shape = CircleShape.copy(CornerSize(32.dp)),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 16.dp),
                    onClick = {
                        isNameTextError = nameValue.text.isEmpty()

                        if (nameValue.text.isNotEmpty()) {
                            viewModel.addName(nameValue.text)
                            nameValue = TextFieldValue()
                        }
                    },

                    ) {
                    Text(text = "Add", fontSize = 16.sp)
                }
            }
            if (isNameTextError) {
                ErrorText()
            }
        }
    }
}

@Composable
private fun ErrorText() {
    Text(
        modifier = Modifier
            .wrapContentSize()
            .padding(start = 32.dp, bottom = 16.dp),
        text = "Name cannot be blank",
        color = MaterialTheme.colorScheme.error
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ListItem(it: Names) {
    OutlinedCard(
        colors = CardDefaults.outlinedCardColors(
            containerColor = Purple80,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(all = 8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = it.name, modifier = Modifier
                .wrapContentSize()
                .padding(all = 16.dp)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeWithHiltTheme {
        Content(viewModel(MainActivityViewModel::class.java))
    }
}