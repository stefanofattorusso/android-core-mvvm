package com.stefattorusso.domain.base

import com.stefattorusso.domain.repository.Repository

abstract class BaseUseCase<out T : Repository>(val repository: T)