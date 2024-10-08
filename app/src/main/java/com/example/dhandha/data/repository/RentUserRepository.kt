package com.example.dhandha.data.repository


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import androidx.room.withTransaction
import com.example.dhandha.data.local.db.DhandhaDatabase
import com.example.dhandha.data.local.entity.RentUserEntity
import com.example.dhandha.data.models.RentUserListCell
import com.example.dhandha.data.paging.RentUserPagingSource
import com.example.dhandha.helper.convertLongDateToString
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject


interface RentUserRepository {
    suspend fun getAllUsers(searchQuery: String): Flow<PagingData<RentUserListCell>>
    suspend fun createUser(user: RentUserEntity)
    suspend fun getUserDetail(userId: UUID): RentUserEntity
    fun editUser()
    fun deleteUser()
}

class RentUserRepositoryImpl @Inject constructor(private val db: DhandhaDatabase) : RentUserRepository {

    private val dao = db.rentUserDao()
    override suspend fun getAllUsers(searchQuery: String): Flow<PagingData<RentUserListCell>> {

        return  Pager(
            config = PagingConfig(
                pageSize = 3,
                initialLoadSize = 10
            ),

            pagingSourceFactory = {
                RentUserPagingSource(dao, searchQuery)
            }
        ).flow.map {
            it.map {item ->
                RentUserListCell(
                    id = item.id,
                    pendingAmount = item.pendingAmount,
                    name = item.name,
                    rentAmount = item.rentAmount,
                    phone = item.phone,
                    expiryDate = convertLongDateToString(item.rentExpiryDate),
                    image = item.image
                )
            }
        }
    }

    override suspend fun createUser(user: RentUserEntity) {
        db.withTransaction {
            dao.insertUser(user)
        }
    }

    override suspend fun getUserDetail(userId: UUID): RentUserEntity {
        return dao.fetchUserDetail(userId)
    }

    override fun editUser() {

    }

    override fun deleteUser() {

    }

}