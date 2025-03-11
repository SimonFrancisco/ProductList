package francisco.simon.productlist.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")
data class ProductDbModel(
    @PrimaryKey
    val id: Int,
    val name: String,
    val time: Long,
    val tags: List<String>,
    val amount: Int
)
