package francisco.simon.productlist.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_list")
data class ProductDbModel(
    @PrimaryKey
    val id: Int,
    val name: String,
    val time: String,
    val tags: List<String>,
    val amount: Int
)
