package francisco.simon.productlist.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import francisco.simon.productlist.presentation.productScreen.ProductScreenViewModel

@Module
interface ViewModelModule {

    @[Binds IntoMap ViewModelKey(ProductScreenViewModel::class)]
    fun bindProductScreenViewModel(viewModel: ProductScreenViewModel): ViewModel
}