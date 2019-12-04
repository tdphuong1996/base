package vn.ekingdom.mobile.icafeemployee.di.module

import vn.ekingdom.mobile.icafeemployee.data.repository.api.DateTypeDeserializer
import vn.ekingdom.mobile.icafeemployee.data.repository.error.NullOnEmptyConverterFactory
import vn.ekingdom.mobile.icafeemployee.utils.BASE_URL
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.text.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit

val retrofitModule = module {

    fun provideGson(): Gson {
        return GsonBuilder()
            .serializeNulls()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .setDateFormat(DateFormat.LONG)
            .registerTypeAdapter(Date::class.java, DateTypeDeserializer())
            .registerTypeAdapter(Date::class.java, DateTypeDeserializer())
            .create()
    }

    fun provideHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(interceptor).build()
    }

    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(ScalarsConverterFactory.create())
//            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

    single { provideGson() }
    single { provideHttpClient() }
    single { provideRetrofit(get(), get()) }
}

val apiModule = module {
//    single { createWebService<PostApi>(get()) }
//    single { createWebService<WeatherDatasource>(get()) }
}

inline fun <reified T> createWebService(retrofit: Retrofit): T {
    return retrofit.create(T::class.java)
}

val remoteModule = listOf(retrofitModule, apiModule)