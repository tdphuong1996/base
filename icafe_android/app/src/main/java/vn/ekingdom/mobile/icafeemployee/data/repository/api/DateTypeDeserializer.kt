package vn.ekingdom.mobile.icafeemployee.data.repository.api

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateTypeDeserializer : JsonDeserializer<Date> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        jsonElement: JsonElement,
        typeOF: Type,
        context: JsonDeserializationContext
    ): Date {
        for (format in DATE_FORMATS) {
            try {
                return SimpleDateFormat(format, Locale.US)
                    .parse(jsonElement.asString) ?: Date()
            } catch (e: ParseException) { //                Log.e("Date error", jsonElement.getAsString());
            }
        }
        throw JsonParseException(
            "Unparseable date: \"" + jsonElement.asString
                    + "\". Supported formats: \n" + Arrays.toString(DATE_FORMATS)
        )
    }

    companion object {
        private val DATE_FORMATS = arrayOf(
            "yyyy-MM-dd'T'HH:mm:ss.SS",
            "yyyy-MM-dd'T'HH:mm:ss.SSSSSS",
            "yyyy-MM-dd'T'HH:mm:ss.SSSSSSS",
            "yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'",
            "yyyy-MM-dd'T'HH:mm:ssZ",
            "yyyy-MM-dd'T'HH:mm:ss",
            "yyyy-MM-dd",
            "EEE MMM dd HH:mm:ss z yyyy",
            "HH:mm:ss",
            "MM/dd/yyyy HH:mm:ss aaa",
            "MMM d',' yyyy H:mm:ss a"
        )
    }
}