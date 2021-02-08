package com.example.breakingbadapp.retorfit

import com.example.breakingbadapp.models.CharacterData
import com.example.breakingbadapp.util.EntityMapper
import javax.inject.Inject

class NetworkMapper
@Inject
constructor() :
    EntityMapper<CharacterNetworkEntity, CharacterData> {

    override fun mapFromEntity(entity: CharacterNetworkEntity): CharacterData {
        return CharacterData(
            charId = entity.charId,
            appearance = entity.appearance,
            betterCallSaulAppearance = entity.betterCallSaulAppearance,
            birthday = entity.birthday,
            category = entity.category,
            img = entity.img,
            name = entity.name,
            nickname = entity.nickname,
            occupation = entity.occupation,
            portrayed = entity.portrayed,
            status = entity.status
        )
    }

    override fun mapToEntity(domainModel: CharacterData): CharacterNetworkEntity {
        return CharacterNetworkEntity(
            charId = domainModel.charId,
            appearance = domainModel.appearance,
            betterCallSaulAppearance = domainModel.betterCallSaulAppearance,
            birthday = domainModel.birthday,
            category = domainModel.category,
            img = domainModel.img,
            name = domainModel.name,
            nickname = domainModel.nickname,
            occupation = domainModel.occupation,
            portrayed = domainModel.portrayed,
            status = domainModel.status
        )
    }

    fun mapFromEntityList(entities: ArrayList<CharacterNetworkEntity>): ArrayList<CharacterData> {
        return entities.map { mapFromEntity(it) } as ArrayList<CharacterData>
    }
}