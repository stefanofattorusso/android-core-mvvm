package com.stefattorusso.domain.repository

import com.stefattorusso.domain.Image

interface ImageRepository : Repository {

    suspend fun retrieveList(): List<Image>
}