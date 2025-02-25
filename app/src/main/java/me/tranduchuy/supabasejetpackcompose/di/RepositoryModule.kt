package me.tranduchuy.supabasejetpackcompose.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.tranduchuy.supabasejetpackcompose.data.repository.AuthenticationRepository
import me.tranduchuy.supabasejetpackcompose.data.repository.ProductRepository
import me.tranduchuy.supabasejetpackcompose.data.repository.impl.AuthenticationRepositoryImpl
import me.tranduchuy.supabasejetpackcompose.data.repository.impl.ProductRepositoryImpl

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindProductRepository(impl: ProductRepositoryImpl): ProductRepository

    @Binds
    abstract fun bindAuthenticateRepository(impl: AuthenticationRepositoryImpl): AuthenticationRepository

}