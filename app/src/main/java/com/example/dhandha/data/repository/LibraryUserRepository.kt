package com.example.dhandha.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.room.withTransaction
import com.example.dhandha.data.local.db.DhandhaDatabase
import com.example.dhandha.data.local.library.LibraryUserEntity
import com.example.dhandha.data.paging.LibraryPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface LibraryUserRepository {
    suspend fun createUser(user: LibraryUserEntity)

    suspend fun fetchAllPaginatedUsers(searchQuery: String): Flow<PagingData<LibraryUserEntity>>
}

class LibraryUserRepositoryImpl @Inject constructor(private val db: DhandhaDatabase): LibraryUserRepository {

    private val dao = db.libraryDao()
    override suspend fun createUser(user: LibraryUserEntity) {
        db.withTransaction {
            dao.insertUser(user)
        }
    }

    override suspend fun fetchAllPaginatedUsers(searchQuery: String): Flow<PagingData<LibraryUserEntity>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                LibraryPagingSource(dao, searchQuery)
            }
        ).flow
    }
}