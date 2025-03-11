package francisco.simon.productlist.domain.usecases

import francisco.simon.productlist.domain.entity.Product
import francisco.simon.productlist.domain.repository.ProductRepository
import javax.inject.Inject

class EditProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend fun invoke(product: Product) {
        repository.editProduct(product)
    }
}