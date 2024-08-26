package com.example.dhandha.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.room.withTransaction
import com.example.dhandha.data.local.db.DhandhaDatabase
import com.example.dhandha.data.local.gym.GymUserEntity
import com.example.dhandha.data.paging.GymUserPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GymUserRepository {

    suspend fun createUser(user: GymUserEntity)
    suspend fun editUser()
    suspend fun deleteUser()
    suspend fun fetchPaginatedUsers(searchQuery: String): Flow<PagingData<GymUserEntity>>
    suspend fun fetchUserDetail()
}

class GymUserRepositoryImpl @Inject constructor(private val db: DhandhaDatabase): GymUserRepository {

    private val dao = db.gymUserDao()

    override suspend fun createUser(user: GymUserEntity) {
        db.withTransaction {
            dao.insertUser(user)
        }
    }

    override suspend fun editUser() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser() {
        TODO("Not yet implemented")
    }

    override suspend fun fetchPaginatedUsers(searchQuery: String): Flow<PagingData<GymUserEntity>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                GymUserPagingSource(dao, searchQuery)
            }
        ).flow
    }

    override suspend fun fetchUserDetail() {
        TODO("Not yet implemented")
    }
}