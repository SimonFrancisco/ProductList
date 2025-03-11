package francisco.simon.productlist.data.mapper

import francisco.simon.productlist.data.local.model.ProductDbModel
import francisco.simon.productlist.domain.entity.Product
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Product.toDbModel(): ProductDbModel {
    return ProductDbModel(
        id = id,
        name = name,
        time = time.toLong(),
        tags = tags,
        amount = amount
    )
}

fun ProductDbModel.toEntity(): Product {
    return Product(
        id = id,
        name = name,
        time = time.mapTimeStampToDate(),
        tags = tags,
        amount = amount
    )
}

fun List<ProductDbModel>.toEntities(): List<Product> {
    return map { it.toEntity() }
}

private fun Long.mapTimeStampToDate(): String {
    val date = Date(this)
    return SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(
        date
    )
}