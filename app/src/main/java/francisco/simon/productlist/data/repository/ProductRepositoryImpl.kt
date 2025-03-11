package francisco.simon.productlist.data.repository

import francisco.simon.productlist.data.local.db.ProductListDao
import francisco.simon.productlist.data.mapper.toDbModel
import francisco.simon.productlist.data.mapper.toEntities
import francisco.simon.productlist.domain.entity.Product
import francisco.simon.productlist.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productListDao: ProductListDao,
) : ProductRepository {

    override val products: Flow<List<Product>>
        get() = productListDao.getProductList()
            .map {
                it.toEntities()
            }

    override suspend fun deleteProduct(product: Product) {
        productListDao.deleteProduct(product.toDbModel())
    }

    override suspend fun editProduct(product: Product) {
        productListDao.editProduct(product.toDbModel())
    }

    override fun searchProducts(query: String): Flow<List<Product>> {
        return productListDao.searchProduct(query).map {
            it.toEntities()
        }
    }
}