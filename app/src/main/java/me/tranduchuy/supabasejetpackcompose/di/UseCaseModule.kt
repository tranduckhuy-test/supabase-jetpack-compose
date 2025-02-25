package me.tranduchuy.supabasejetpackcompose.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.tranduchuy.supabasejetpackcompose.domain.usecase.CreateProductUseCase
import me.tranduchuy.supabasejetpackcompose.domain.usecase.SignInUseCase
import me.tranduchuy.supabasejetpackcompose.domain.usecase.SignInWithGoogleUseCase
import me.tranduchuy.supabasejetpackcompose.domain.usecase.SignUpUseCase
import me.tranduchuy.supabasejetpackcompose.domain.usecase.impl.CreateProductUseCaseImpl
import me.tranduchuy.supabasejetpackcompose.domain.usecase.impl.SignInUseCaseImpl
import me.tranduchuy.supabasejetpackcompose.domain.usecase.impl.SignInWithGoogleUseCaseImpl
import me.tranduchuy.supabasejetpackcompose.domain.usecase.impl.SignUpUseCaseImpl

@InstallIn(SingletonComponent::class)
@Module
abstract class UseCaseModule {

//    @Binds
//    abstract fun bindGetProductsUseCase(impl: GetProductsUseCaseImpl): GetProductsUseCase

    @Binds
    abstract fun bindCreateProductUseCase(impl: CreateProductUseCaseImpl): CreateProductUseCase

//    @Binds
//    abstract fun bindGetProductDetailsUseCase(impl: GetProductDetailsUseCaseImpl): GetProductDetailsUseCase
//
//    @Binds
//    abstract fun bindDeleteProductUseCase(impl: DeleteProductUseCaseImpl): DeleteProductUseCase
//
//    @Binds
//    abstract fun bindUpdateProductUseCase(impl: UpdateProductUseCaseImpl): UpdateProductUseCase

    @Binds
    abstract fun bindAuthenticateUseCase(impl: SignInUseCaseImpl): SignInUseCase

    @Binds
    abstract fun bindSignUpUseCase(impl: SignUpUseCaseImpl): SignUpUseCase

    @Binds
    abstract fun bindSignInWithGoogleUseCase(impl: SignInWithGoogleUseCaseImpl): SignInWithGoogleUseCase
}