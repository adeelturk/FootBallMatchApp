package com.turk.dtos.mapper

/**
 * Provide the Implementation to map Network Dto to App Dto
 * [Entity] is Variant for Network Dto
 * [DomainModel] is  Variant for App Dto
 */

interface DataMapper<Entity,DomainModel> {

    fun mapFromEntity(entity:Entity):DomainModel




}