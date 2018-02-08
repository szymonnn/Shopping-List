package pl.nejos.shoppinglist.data.database

import android.arch.persistence.room.TypeConverter
import java.util.*


/**
 * Created by SzymonNitecki on 04.02.2018.
 */
class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return if (date == null) {
            null
        } else {
            date!!.getTime()
        }
    }
}
