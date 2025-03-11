package francisco.simon.productlist.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import francisco.simon.productlist.data.local.model.ProductDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductListDao {

    @Query("SELECT * FROM product_list ORDER BY id ASC")
    fun getProductList(): Flow<List<ProductDbModel>>

    @Query("SELECT * FROM product_list WHERE name LIKE:query ORDER BY name ASC")
    fun searchProduct(query: String): Flow<List<ProductDbModel>>

    @Delete
    suspend fun deleteProduct(productDbModel: ProductDbModel)

    @Update
    suspend fun editProduct(productDbModel: ProductDbModel)
}