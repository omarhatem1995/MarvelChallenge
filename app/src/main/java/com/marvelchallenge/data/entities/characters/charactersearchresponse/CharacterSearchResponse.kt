package com.marvelchallenge.data.entities.characters.charactersearchresponse

data class CharacterSearchResponse(
    val copyright: String? = null,
    val code: Int? = null,
    val data: Data? = null,
    val attributionHTML: String? = null,
    val attributionText: String? = null,
    val etag: String? = null,
    val status: String? = null
)

data class ItemsItem(
    val name: String? = null,
    val resourceURI: String? = null
)

data class UrlsItem(
    val type: String? = null,
    val url: String? = null
)

data class Events(
    val collectionURI: String? = null,
    val available: Int? = null,
    val returned: Int? = null,
    val items: List<ItemsItem?>? = null
)

data class Stories(
    val collectionURI: String? = null,
    val available: Int? = null,
    val returned: Int? = null,
    val items: List<ItemsItem?>? = null
)

data class Thumbnail(
    val path: String? = null,
    val extension: String? = null
)

data class Series(
    val collectionURI: String? = null,
    val available: Int? = null,
    val returned: Int? = null,
    val items: List<ItemsItem?>? = null
)

data class ResultsItem(
    val thumbnail: Thumbnail? = null,
    val urls: List<UrlsItem?>? = null,
    val stories: Stories? = null,
    val series: Series? = null,
    val comics: Comics? = null,
    val name: String,
    val description: String? = null,
    val modified: String? = null,
    val id: Int ,
    val resourceURI: String? = null,
    val events: Events? = null
)

data class Data(
    val total: Int? = null,
    val offset: Int? = null,
    val limit: Int? = null,
    val count: Int? = null,
    val results: List<ResultsItem>? = null
)

data class Comics(
    val collectionURI: String? = null,
    val available: Int? = null,
    val returned: Int? = null,
    val items: List<ItemsItem?>? = null
)

