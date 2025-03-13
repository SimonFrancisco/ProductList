package francisco.simon.productlist.presentation.productScreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import francisco.simon.productlist.R

@Composable
fun SearchProduct(
    modifier: Modifier = Modifier,
    viewModel: ProductScreenViewModel
) {
    val query = rememberSaveable {
        mutableStateOf("")
    }
    val trailingIconVisibility by remember {
        derivedStateOf {
            query.value.isNotBlank()
        }
    }
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = query.value,
        onValueChange = { newQuery ->
            query.value = newQuery
            viewModel.searchProductQuery(query.value)
        },
        label = {
            Text(
                text = stringResource(R.string.look_for_product_label),
                color = Color.Black
            )

        },
        singleLine = true,
        leadingIcon = {
            IconButton(onClick = {
                viewModel.searchProductQuery(query.value)
            })
            {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = stringResource(R.string.search_product_icon)
                )
            }

        },
        trailingIcon = {
            IconButton(onClick = {
                query.value = ""
                viewModel.searchProductQuery(query.value)
            }) {
                if (trailingIconVisibility) {
                    Icon(
                        imageVector = Icons.Outlined.Clear,
                        contentDescription = stringResource(R.string.clear_search_icon)
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                viewModel.searchProductQuery(query.value)
            }
        )
    )
}