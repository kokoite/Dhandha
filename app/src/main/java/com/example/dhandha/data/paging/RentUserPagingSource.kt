package com.example.dhandha.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.dhandha.data.local.rent.RentUserDao
import com.example.dhandha.data.local.rent.RentUserEntity
import kotlinx.coroutines.delay
import javax.inject.Inject

const val TAG = "TAG"
class RentUserPagingSource(private val dao: RentUserDao) : PagingSource<Int, RentUserEntity>() {

    override fun getRefreshKey(state: PagingState<Int, RentUserEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RentUserEntity> {
        return try {

            val currentPage: Int = params.key ?: 0
            if(currentPage > 0) {
                delay(1000)
            }
            val offset = (currentPage)*params.loadSize

            val rentUsers = dao.fetchPaginatedUsers(offset, params.loadSize)
            val prevKey = if(currentPage == 0) null else currentPage-1
            val nextKey = if(rentUsers.size < params.loadSize) null else currentPage + 1
            Log.d(TAG, "pagination loading ${offset} ${rentUsers.count()} ${currentPage} $prevKey $nextKey ${params.loadSize} ")

            LoadResult.Page(
                data = rentUsers,
                prevKey,
                nextKey
            )
        } catch (error: Error) {
            LoadResult.Error(error)
        }
    }
}
