package francisco.simon.productlist.domain.entity

data class Product(
    val id: Int,
    val name: String,
    val time: String,
    val tags: List<String>,
    val amount: Int
)
