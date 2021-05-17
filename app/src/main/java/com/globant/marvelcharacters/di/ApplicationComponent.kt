package com.globant.marvelcharacters.di

import android.content.Context
import com.globant.marvelcharacters.characterdetail.CharacterDetailsFragment
import com.globant.marvelcharacters.characterlist.CharacterListFragment
import com.globant.marvelcharacters.data.di.DataModule
import com.globant.marvelcharacters.data.di.NetworkModule
import com.globant.marvelcharacters.domain.di.DomainModule
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [DataModule::class,
        NetworkModule::class,
        DomainModule::class]
)
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    fun inject(activity: CharacterListFragment)
    fun inject(activity: CharacterDetailsFragment)
}