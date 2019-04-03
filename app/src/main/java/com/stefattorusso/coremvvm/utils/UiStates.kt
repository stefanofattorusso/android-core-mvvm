package com.stefattorusso.coremvvm.utils

/**
 * Base class to represent common UI states
 */
sealed class UIState

/**
 * the screen is currently loading it's data. "loading state"
 */
object Loading : UIState()

/**
 * data was successfully loaded for the screen.  "happy path"
 */
object HasData : UIState()

/**
 * the load was successful, but there was no data.  "empty state"
 */
object NoData : UIState()

/**
 * some type of error occurred.  "error state"
 */
object Error : UIState()