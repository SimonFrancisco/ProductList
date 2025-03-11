package francisco.simon.productlist.domain.repository

import francisco.simon.productlist.domain.entity.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    val products: Flow<List<Product>>

    suspend fun deleteProduct(product: Product)

    suspend fun editProduct(product: Product)

    fun searchProducts(query: String): Flow<List<Product>>

}