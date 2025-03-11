package francisco.simon.productlist.presentation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import francisco.simon.productlist.di.ApplicationComponent
import francisco.simon.productlist.di.DaggerApplicationComponent

class ProductListApplication : Application() {
    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}

@Composable
fun getApplicationComponent(): ApplicationComponent {
    return (LocalContext.current.applicationContext as ProductListApplication).component
}
