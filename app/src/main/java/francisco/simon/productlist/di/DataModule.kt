package francisco.simon.productlist.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import francisco.simon.productlist.data.local.db.ProductListDao
import francisco.simon.productlist.data.local.db.ProductListDatabase
import francisco.simon.productlist.data.repository.ProductRepositoryImpl
import francisco.simon.productlist.domain.repository.ProductRepository

@Module
interface DataModule {

    @[ApplicationScope Binds]
    fun bindsProductRepository(impl: ProductRepositoryImpl): ProductRepository

    companion object {

        @[ApplicationScope Provides]
        fun provideProductListDatabase(context: Context): ProductListDatabase {
            return ProductListDatabase.getInstance(context)
        }

        @[ApplicationScope Provides]
        fun providesProductListDao(database: ProductListDatabase): ProductListDao {
            return database.productListDao()
        }
    }

}