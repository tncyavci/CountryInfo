package com.tuncayavci.countryinfo.services

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tuncayavci.countryinfo.model.Country

@Dao
interface CountryDao {

    // data Access Object

    @Insert
    suspend fun insertAll(vararg countries : Country) : List<Long>

    //Insert -> INSERT INTO
    //suspend -> coroutine , pause & resume
    //vararg ->multiple country object
    // List<Long> ->  primary keys

    @Query("SELECT * FROM country")
    suspend fun getAllCountries() : List<Country>

    @Query("SELECT * FROM country WHERE uuid = :countryId")
    suspend fun getCountry(countryId : Int) : Country

    @Query("DELETE FROM country")
    suspend fun deleteAllCountries()
}