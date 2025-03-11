package francisco.simon.productlist.presentation.productScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import francisco.simon.productlist.R
import francisco.simon.productlist.domain.entity.Product
import francisco.simon.productlist.presentation.getApplicationComponent
import francisco.simon.productlist.ui.theme.BrightGray
import francisco.simon.productlist.ui.theme.DarkBlue
import francisco.simon.productlist.ui.theme.LightSteelBlue


@Composable
fun ProductScreen(modifier: Modifier = Modifier) {
    val viewModelFactory = getApplicationComponent().getViewModelFactory()
    val viewModel: ProductScreenViewModel = viewModel(factory = viewModelFactory)
    val screenState = viewModel.screenState
        .collectAsState(ProductListScreenState.Initial)
    ProductScreenContent(
        screenState = screenState,
        viewModel = viewModel
    )

}

@Composable
fun ProductScreenContent(
    modifier: Modifier = Modifier,
    screenState: State<ProductListScreenState>,
    viewModel: ProductScreenViewModel
) {
    when (val currentState = screenState.value) {
        ProductListScreenState.Initial -> {

        }

        ProductListScreenState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = DarkBlue
                )
            }
        }

        is ProductListScreenState.Products -> {
            ProductItems(
                products = currentState.products,
                viewModel = viewModel
            )
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductItems(
    products: List<Product>,
    modifier: Modifier = Modifier,
    viewModel: ProductScreenViewModel
) {
    Scaffold(
        containerColor = BrightGray,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = LightSteelBlue
                ),
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(R.string.product_list),
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            )
        }) { paddingValues ->
        val openDeleteAlertDialog = remember { mutableStateOf(false) }
        val currentProduct: MutableState<Product?> = remember { mutableStateOf(null) }
        AlertDialogDelete(openDeleteAlertDialog, currentProduct, viewModel)
        val openEditAlertDialog = remember { mutableStateOf(false) }
        AlertDialogEdit(openEditAlertDialog, currentProduct, viewModel)

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues),
            contentPadding = PaddingValues(
                top = 16.dp,
                start = 8.dp,
                end = 8.dp,
                bottom = 72.dp
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            items(products, key = { it.id }) { product ->
                ProductCard(
                    product = product,
                    onEditClickListener = {
                        openEditAlertDialog.value = true
                        currentProduct.value = product
                    },
                    onDeleteClickListener = {
                        openDeleteAlertDialog.value = true
                        currentProduct.value = product
                    }
                )
            }

        }

    }
}

@Composable
private fun AlertDialogEdit(
    openEditAlertDialog: MutableState<Boolean>,
    currentProduct: MutableState<Product?>,
    viewModel: ProductScreenViewModel
) {
    when {
        openEditAlertDialog.value -> {
            val newCurrentProduct = currentProduct.value
            val amountOfProduct = remember { mutableIntStateOf(0) }
            if (newCurrentProduct != null) {
                amountOfProduct.intValue = newCurrentProduct.amount
            }
            EditAlertDialog(
                onDismissRequest = {
                    openEditAlertDialog.value = false
                },
                onConfirmation = {
                    openEditAlertDialog.value = false
                    if (newCurrentProduct != null) {
                        viewModel.editProduct(newCurrentProduct.copy(amount = amountOfProduct.intValue))
                    }
                },
                onAmountChange = { newAmount ->
                    amountOfProduct.intValue = newAmount
                },
                amount = amountOfProduct
                )
        }
    }
}

@Composable
private fun AlertDialogDelete(
    openAlertDialog: MutableState<Boolean>,
    currentProduct: MutableState<Product?>,
    viewModel: ProductScreenViewModel
) {
    when {
        openAlertDialog.value -> {
            DeleteAlertDialog(
                onDismissRequest = { openAlertDialog.value = false },
                onConfirmation = {
                    openAlertDialog.value = false
                    val newCurrentProduct = currentProduct.value
                    if (newCurrentProduct != null) {
                        viewModel.deleteProduct(newCurrentProduct)
                    }
                }
            )
        }
    }
}