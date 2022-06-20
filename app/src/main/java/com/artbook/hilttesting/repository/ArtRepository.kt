package com.artbook.hilttesting.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.artbook.hilttesting.api.RetrofitAPI
import com.artbook.hilttesting.model.ImageResponse
import com.artbook.hilttesting.roomdb.Art
import com.artbook.hilttesting.roomdb.ArtDao
import com.artbook.hilttesting.util.Resource
import javax.inject.Inject

class ArtRepository @Inject constructor(
    private val artDao: ArtDao,
    private val retrofitAPI: RetrofitAPI
) : ArtRepositoryInterface {

    override suspend fun insertArt(art: Art) {
        artDao.insertArt(art)
    }

    override suspend fun deleteArt(art: Art) {
        artDao.deleteArt(art)
    }

    override fun getArt(): LiveData<List<Art>> {
        return artDao.observeArts()
    }

    override suspend fun searchImage(imageString: String): Resource<ImageResponse> {
        return try {
            val response = retrofitAPI.imageSearch(imageString)
            if (response.isSuccessful) {
                response.body()?.let { data ->
                    return@let Resource.success(data)
                } ?: Resource.error("Error, Data is null", null)
            } else {
                Resource.error("Error, Response not Successful ", null)
            }
        } catch (e: Exception) {
            Resource.error("No Data", null)
        }
    }


}