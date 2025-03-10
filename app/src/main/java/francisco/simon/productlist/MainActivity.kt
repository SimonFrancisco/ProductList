package francisco.simon.productlist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import francisco.simon.productlist.presentation.ProductCard
import francisco.simon.productlist.presentation.fakeProduct
import francisco.simon.productlist.ui.theme.ProductListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProductListTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                containerColor = Color.LightGray) { innerPadding ->
                    ProductCard(
                        modifier = Modifier.padding(innerPadding),
                        product = fakeProduct,
                        onDeleteClickListener = {

                        },
                        onEditClickListener = {

                        }
                    )
                }
            }
        }
    }
}
