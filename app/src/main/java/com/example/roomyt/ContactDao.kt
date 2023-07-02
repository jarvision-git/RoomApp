package com.example.roomyt

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    @Upsert
    suspend fun upsertContact(contact:Contact)

    @Delete
    suspend fun deleteContact(contact:Contact)

    @Query("SELECT * FROM contact")
    fun fetchAllEmployees():Flow<List<Contact>>

    @Query("Select * from contact where id=:id")
    fun fetchEmployeeById(id:Int):Flow<Contact>





}