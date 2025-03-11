package francisco.simon.productlist.presentation.productScreen

import francisco.simon.productlist.domain.entity.Product

sealed class ProductListScreenState {
    data object Initial : ProductListScreenState()
    data object Loading : ProductListScreenState()
    data class Products(
        val products: List<Product>
    ) : ProductListScreenState()
}
