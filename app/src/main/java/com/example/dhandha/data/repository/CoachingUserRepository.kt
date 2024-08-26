package com.example.dhandha.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.room.withTransaction
import com.example.dhandha.data.local.coaching.CoachingUserEntity
import com.example.dhandha.data.local.db.DhandhaDatabase
import com.example.dhandha.data.paging.CoachingUserPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface CoachingUserRepository  {
    suspend fun createUser(user: CoachingUserEntity)

    suspend fun fetchPaginatedUser(searchQuery: String): Flow<PagingData<CoachingUserEntity>>

    suspend fun deleteUser()

    suspend fun fetchUserDetail()

    suspend fun editUser()
}

class CoachingUserRepositoryImpl @Inject constructor(private val db: DhandhaDatabase): CoachingUserRepository {

    private val dao = db.coachingUserDao()

    override suspend fun createUser(user: CoachingUserEntity) {
        db.withTransaction {
            dao.insertUser(user)
        }
    }

    override suspend fun fetchPaginatedUser(searchQuery: String): Flow<PagingData<CoachingUserEntity>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                CoachingUserPagingSource(dao, searchQuery)
            }
        ).flow
    }

    override suspend fun deleteUser() {

    }

    override suspend fun fetchUserDetail() {

    }

    override suspend fun editUser() {

    }


}