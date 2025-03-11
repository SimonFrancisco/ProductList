package francisco.simon.productlist.presentation.productScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import francisco.simon.productlist.R
import francisco.simon.productlist.domain.entity.Product
import francisco.simon.productlist.ui.theme.DarkRed
import francisco.simon.productlist.ui.theme.Purple40


@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    product: Product,
    onDeleteClickListener: () -> Unit,
    onEditClickListener: () -> Unit
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ), elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            ProductHeader(
                product = product,
                onDeleteClickListener = onDeleteClickListener,
                onEditClickListener = onEditClickListener
            )
            Spacer(modifier = Modifier.height(8.dp))
            ProductTags(product)
            Spacer(modifier = Modifier.height(8.dp))
            ProductInfoAfterTags(product)

        }
    }
}

@Composable
private fun ProductInfoAfterTags(product: Product) {
    Row {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(R.string.at_warehouse),
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "${product.amount}")
        }
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(R.string.date_added),
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = product.time)
        }
    }
}

@Composable
@OptIn(ExperimentalLayoutApi::class)
private fun ProductTags(product: Product) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        product.tags.forEach { tag ->
            Card(
                border = BorderStroke(width = 1.dp, color = Color.Gray),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            ) {
                Text(
                    modifier = Modifier.padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 4.dp,
                        bottom = 4.dp
                    ),
                    text = tag,
                    fontWeight = FontWeight.Bold
                )
            }
        }

    }
}

@Composable
private fun ProductHeader(
    product: Product,
    onDeleteClickListener: () -> Unit,
    onEditClickListener: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = product.name,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            modifier = Modifier
                .weight(1f)
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            ClickableIcon(
                onItemClickListener = {
                    onEditClickListener()
                },
                tint = Purple40,
                imageVector = Icons.Filled.Edit,
                contentDescription = "Edit"
            )
            ClickableIcon(
                onItemClickListener = {
                    onDeleteClickListener()
                },
                tint = DarkRed,
                imageVector = Icons.Filled.Delete,
                contentDescription = "Delete"
            )
        }

    }
}

@Composable
fun ClickableIcon(
    modifier: Modifier = Modifier,
    onItemClickListener: () -> Unit,
    tint: Color,
    imageVector: ImageVector,
    contentDescription: String
) {

    Box(modifier = modifier.clickable {
        onItemClickListener()
    })
    {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = tint
        )
    }

}


//@PreviewLightDark
//@Composable
//private fun ProductHeaderReview() {
//    ProductListTheme {
//        ProductCard(
//            product = fakeProduct,
//            onDeleteClickListener = {
//
//            },
//            onEditClickListener = {
//
//            }
//        )
//    }
//
//}
//
//private const val fakeTime = 1633132800000L
//
//internal val fakeProduct = Product(
//    id = 1,
//    name = "iPhone 13",
//    time = "11.03.2025",
//    tags = listOf("Simon", "Chisenga", "Ernest", "David", "Osman"),
//    amount = 15
//)


