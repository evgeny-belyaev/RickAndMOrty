package com.ugene.rickandmorty.di

import dagger.Binds
import dagger.Component
import dagger.Module
import javax.inject.Inject

interface IComponent1 {
    fun method(): String
}

interface IComponent2 {

}


class Component1 @Inject constructor() : IComponent1 {
    override fun method(): String {
        return "Hello!"
    }

}

class Component2 @Inject constructor() : IComponent2 {

}

interface IHeater {

}

class Heater @Inject constructor(
    val component1: IComponent1,
    val component2: IComponent2
) : IHeater {
}

@Module
interface MyModule {
    @Binds
    fun bindComponent1(component1: Component1): IComponent1

    @Binds
    fun bindComponent2(component2: Component2): IComponent2

    @Binds
    fun bindHeater(heater: Heater): IHeater
}

@Component(modules = [MyModule::class])
interface CoffeeShop {
    fun maker(): IHeater
}


