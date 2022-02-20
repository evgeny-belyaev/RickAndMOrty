package com.ugene.rickandmorty.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import kotlin.reflect.KClass

fun <TBinding : ViewBinding> inflateViewBinding(
    kClass: KClass<TBinding>,
    parent: ViewGroup
): TBinding {
    val method = kClass.java.getMethod(
        "inflate",
        LayoutInflater::class.java,
        ViewGroup::class.java,
        Boolean::class.java
    )

    return method.invoke(
        null, LayoutInflater.from(parent.context),
        parent,
        false
    ) as TBinding
}
