package com.example.dhandha.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.dhandha.data.local.dao.CoachingUserDao
import com.example.dhandha.data.local.entity.CoachingUserEntity
import kotlinx.coroutines.delay

class CoachingUserPagingSource(private val dao: CoachingUserDao, private val searchQuery: String): PagingSource<Int, CoachingUserEntity>() {
    override fun getRefreshKey(state: PagingState<Int, CoachingUserEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CoachingUserEntity> {
        return try {

            val currentPage: Int = params.key ?: 0
            if(currentPage > 0) {
                delay(1000)
            }
            val offset = (currentPage)*params.loadSize

            val rentUsers = dao.fetchPaginatedUsers(searchQuery,offset, params.loadSize)
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