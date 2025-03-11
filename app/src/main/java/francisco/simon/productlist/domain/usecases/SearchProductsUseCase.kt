package francisco.simon.productlist.domain.usecases

import francisco.simon.productlist.domain.entity.Product
import francisco.simon.productlist.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(query: String): Flow<List<Product>> {
        return repository.searchProducts(query)
    }
}