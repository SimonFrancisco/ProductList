package francisco.simon.productlist.presentation.productScreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.toArgb
import francisco.simon.productlist.ui.theme.LightSteelBlue
import francisco.simon.productlist.ui.theme.ProductListTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            navigationBarStyle = SystemBarStyle.light(
                LightSteelBlue.toArgb(),
                LightSteelBlue.toArgb()
            ),
            statusBarStyle = SystemBarStyle.light(
                LightSteelBlue.toArgb(),
                LightSteelBlue.toArgb()
            )
        )
        setContent {
            ProductListTheme {
                ProductScreen()
            }
        }
    }
}
