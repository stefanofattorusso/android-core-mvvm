package com.stefattorusso.domain.repository

import com.stefattorusso.domain.Image
import com.stefattorusso.domain.Outcome

interface ImageRepository : Repository {

    suspend fun retrieveList(): Outcome<List<Image>>
}