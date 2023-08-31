package com.bucic.project1_starwarspeople

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors

class StarWarsPeopleRepository {

    suspend fun getPeople(): Flow<List<StarWarsPersonEntity>> = flow {
        val people = mutableListOf<StarWarsPersonEntity>()
        var page = 1

        while (page != 0) {
            try {
                val url = URL("https://swapi.dev/api/people/?page=$page")
                val httpURLConnection = url.openConnection() as HttpURLConnection
                httpURLConnection.requestMethod = "GET"
                httpURLConnection.readTimeout = 15000
                httpURLConnection.connectTimeout = 15000
                httpURLConnection.connect()
                val inputStreamReader = InputStreamReader(httpURLConnection.inputStream)
                val bufferedReader = BufferedReader(inputStreamReader)
                val response = Gson().fromJson(bufferedReader, StarWarsPeopleResponse::class.java)
                people.addAll(response.results)
                page++
                bufferedReader.close()
                inputStreamReader.close()
            } catch (e: FileNotFoundException) {
                page = 0  // Stop the loop on error
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        emit(people)
    }.flowOn(Dispatchers.IO)

//    suspend fun getPeople(): List<StarWarsPersonEntity> = withContext(Dispatchers.IO) {
//        val people = mutableListOf<StarWarsPersonEntity>()
//        var page = 1
//
//        while (page != 0) {
//            try {
//                val url = URL("https://swapi.dev/api/people/?page=$page")
//                val httpURLConnection = url.openConnection() as HttpURLConnection
//                httpURLConnection.requestMethod = "GET"
//                httpURLConnection.readTimeout = 15000
//                httpURLConnection.connectTimeout = 15000
//                httpURLConnection.connect()
//                val inputStreamReader = InputStreamReader(httpURLConnection.inputStream)
//                val bufferedReader = BufferedReader(inputStreamReader)
//                val response = Gson().fromJson(bufferedReader, StarWarsPeopleResponse::class.java)
//                people.addAll(response.results)
//                page++
//                bufferedReader.close()
//                inputStreamReader.close()
//            } catch (e: Exception) {
//                e.printStackTrace()
//                page = 0  // Stop the loop on error
//            }
//        }
//
//        return@withContext people
//    }


//    fun getPeople(): List<StarWarsPersonEntity> {
//        val executor = Executors.newSingleThreadExecutor()
//        val handler = Handler(Looper.getMainLooper())
//        val people = mutableListOf<StarWarsPersonEntity>()
//        executor.execute {
//            // Background work here
//            var page = 1
//            while (page != 0) {
//                try {
//                    val url = URL("https://swapi.dev/api/" +
//                            "people/" + "?" + "page=" + page)
//                    val httpURLConnection = url.openConnection() as HttpURLConnection
//                    httpURLConnection.requestMethod = "GET"
//                    httpURLConnection.readTimeout = 15000
//                    httpURLConnection.connectTimeout = 15000
//                    httpURLConnection.connect()
//                    val inputStreamReader = InputStreamReader(httpURLConnection.inputStream)
//                    val bufferedReader = BufferedReader(inputStreamReader)
//                    val response = Gson().fromJson(bufferedReader, StarWarsPeopleResponse::class.java)
//                    people.addAll(response.results)
//                    page++
//                    bufferedReader.close()
//                    inputStreamReader.close()
////                    if (response.isGreska) {
////                        return@execute
////                    }
////                    handler.post {
////                        adapterListe.setOsobe(response.osobe)
////                        adapterListe.notifyDataSetChanged()
////                    }
//                } catch (e: Exception) {
//                    Log.d("REST exception", e.message.toString())
//                    e.printStackTrace()
//                    return@execute
//                }
//            }
//        }
//        return people
//    }
}