package com.artbook.hilttesting.repository

import androidx.lifecycle.LiveData
import com.artbook.hilttesting.model.ImageResponse
import com.artbook.hilttesting.roomdb.Art
import com.artbook.hilttesting.util.Resource

interface ArtRepositoryInterface {

    suspend fun insertArt(art: Art)

    suspend fun deleteArt(art: Art)

    fun getArt(): LiveData<List<Art>>

    suspend fun searchImage(imageString: String): Resource<ImageResponse>


}