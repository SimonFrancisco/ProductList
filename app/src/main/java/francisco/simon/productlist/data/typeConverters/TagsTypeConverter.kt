package francisco.simon.productlist.data.typeConverters

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json
import javax.inject.Inject

class TagsTypeConverter @Inject constructor() {
    @TypeConverter
    fun jsonToList(tags: List<String>): String {
        return Json.encodeToString(tags)
    }

    @TypeConverter
    fun toTags(tags: String): List<String> {
        return Json.decodeFromString<List<String>>(tags)
    }
}