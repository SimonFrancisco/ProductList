package francisco.simon.productlist.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import francisco.simon.productlist.presentation.ViewModelFactory

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun getViewModelFactory(): ViewModelFactory

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context
        ): ApplicationComponent
    }

}