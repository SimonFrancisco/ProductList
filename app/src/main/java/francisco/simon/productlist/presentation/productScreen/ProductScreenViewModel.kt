package francisco.simon.productlist.presentation.productScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import francisco.simon.productlist.domain.entity.Product
import francisco.simon.productlist.domain.usecases.DeleteProductUseCase
import francisco.simon.productlist.domain.usecases.EditProductUseCase
import francisco.simon.productlist.domain.usecases.GetProductsUseCase
import francisco.simon.productlist.domain.usecases.SearchProductsUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductScreenViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val deleteProductUseCase: DeleteProductUseCase,
    private val editProductUseCase: EditProductUseCase,
    private val searchProductsUseCase: SearchProductsUseCase
) : ViewModel() {

    private var searchJob: Job? = null

    private val exceptionHandler = CoroutineExceptionHandler{_,_->
        Log.d("ProductScreenViewModel", "Exception caught by Exception Handler")
    }

    private val foundProducts: MutableStateFlow<ProductListScreenState.Products> =
        MutableStateFlow(ProductListScreenState.Products(emptyList()))

    private var isSearching = MutableStateFlow(false)

    val screenState = combine(
        getProductsUseCase(),
        foundProducts,
        isSearching
    ) { dbList, foundList, searching ->
        if (searching) {
            foundList as ProductListScreenState
        } else {
            ProductListScreenState.Products(dbList) as ProductListScreenState
        }
    }.onStart {
        delay(LOADING_TIME)
        emit(ProductListScreenState.Loading)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = ProductListScreenState.Loading
    )


    fun editProduct(product: Product) {
        viewModelScope.launch(exceptionHandler) {
            editProductUseCase(product)
        }
    }

    fun deleteProduct(product: Product) {
        viewModelScope.launch(exceptionHandler) {
            deleteProductUseCase(product)
        }
    }

    fun searchProductQuery(query: String) {
        val queryRoom = "%${query}%"
        isSearching.value = query.isNotEmpty()
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            if (query.isNotEmpty()) {
                searchProductsUseCase(queryRoom).map {
                    ProductListScreenState.Products(it)
                }.collect {
                    foundProducts.emit(it)
                }
            }
        }
    }

    companion object {
        private const val LOADING_TIME = 1000L
    }

}