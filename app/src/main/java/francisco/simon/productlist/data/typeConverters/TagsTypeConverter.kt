package francisco.simon.productlist.data.typeConverters

import androidx.room.TypeConverter
import javax.inject.Inject

class TagsTypeConverter @Inject constructor() {
    @TypeConverter
    fun fromTags(tags: List<String>): String {
        return tags.joinToString(",")
    }

    @TypeConverter
    fun toTags(data: String): List<String> {
        return data.split(",")
    }
}