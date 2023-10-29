package uz.shahbozbek.namozvaqtlari.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.shahbozbek.namozvaqtlari.data.Repository
import uz.shahbozbek.namozvaqtlari.data.remote.ApiInterface
import uz.shahbozbek.namozvaqtlari.data.remote.RetrofitBuilder
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRemoteRepository(apiInterface: ApiInterface): Repository {
        return Repository(apiInterface = apiInterface)
    }

    @Provides
    @Singleton
    fun provideRetrofitGetServer(): ApiInterface {
        return RetrofitBuilder.apiInterfaceBuilder()
    }

}