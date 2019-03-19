package com.stefattorusso.domain.interactor.impl

import com.stefattorusso.domain.repository.Repository

abstract class BaseUseCaseImpl<T: Repository>(val repository: T)