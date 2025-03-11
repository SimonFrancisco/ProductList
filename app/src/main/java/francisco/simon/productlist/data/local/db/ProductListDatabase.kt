package francisco.simon.productlist.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import francisco.simon.productlist.data.local.model.ProductDbModel

@Database(entities = [ProductDbModel::class], version = 1, exportSchema = false)
abstract class ProductListDatabase : RoomDatabase() {

    abstract fun productListDao(): ProductListDao

    companion object {
        private const val DB_NAME = "ProductListDatabase"
        private var INSTANCE: ProductListDatabase? = null
        private val LOCK = Any()

        fun getInstance(context: Context): ProductListDatabase {
            INSTANCE?.let {
                return it
            }

            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val database = Room.databaseBuilder(
                    context = context,
                    klass = ProductListDatabase::class.java,
                    name = DB_NAME
                ).createFromAsset("database/data.db").build()
                INSTANCE = database
                return database
            }
        }
    }

}