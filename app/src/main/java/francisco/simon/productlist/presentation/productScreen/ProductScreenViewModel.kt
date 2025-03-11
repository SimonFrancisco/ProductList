package francisco.simon.productlist.presentation.productScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import francisco.simon.productlist.domain.entity.Product
import francisco.simon.productlist.domain.usecases.DeleteProductUseCase
import francisco.simon.productlist.domain.usecases.EditProductUseCase
import francisco.simon.productlist.domain.usecases.GetProductsUseCase
import francisco.simon.productlist.domain.usecases.SearchProductsUseCase
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductScreenViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val deleteProductUseCase: DeleteProductUseCase,
    private val editProductUseCase: EditProductUseCase,
    private val searchProductsUseCase: SearchProductsUseCase
) : ViewModel() {

    val screenState = getProductsUseCase()
        .map {
            ProductListScreenState.Products(it) as ProductListScreenState
        }
        .onStart { emit(ProductListScreenState.Loading) }


    fun editProduct(product: Product) {
        viewModelScope.launch {
            editProductUseCase(product)
        }
    }

    fun deleteProduct(product: Product) {
        viewModelScope.launch {
            deleteProductUseCase(product)
        }
    }
}