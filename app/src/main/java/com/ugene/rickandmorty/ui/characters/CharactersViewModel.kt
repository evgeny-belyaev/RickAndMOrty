package com.ugene.rickandmorty.ui.characters

import com.ugene.rickandmorty.api.ApiService
import com.ugene.rickandmorty.api.BaseEntity
import com.ugene.rickandmorty.api.CharacterEntity
import com.ugene.rickandmorty.api.ServiceFactory
import com.ugene.rickandmorty.ui.PagedListViewModel
import io.reactivex.Observable
import javax.inject.Inject

class CharactersViewModel @Inject constructor(private val serviceFactory: ServiceFactory) :
    PagedListViewModel<CharacterEntity, CharactersAdapter.Character>() {

    override fun getFirstPage(): Observable<BaseEntity<CharacterEntity>> {
        return serviceFactory.create<ApiService>().getCharactersFirstPage()
    }

    override fun getPage(nextPageUrl: String): Observable<BaseEntity<CharacterEntity>> {
        return serviceFactory.create<ApiService>().getCharactersPage(nextPageUrl)
    }

    override fun convertToAdapterItem(entity: CharacterEntity): CharactersAdapter.Character {
        return CharactersAdapter.Character(
            entity.id,
            entity.name,
            entity.species,
            entity.status,
            entity.gender,
            entity.image
        )
    }
}
