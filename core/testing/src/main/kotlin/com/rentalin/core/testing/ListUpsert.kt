package com.rentalin.core.testing

internal fun <T> List<T>.upsert(item: T, sameItem: (T) -> Boolean): List<T> =
    if (any(sameItem)) {
        map { existing -> if (sameItem(existing)) item else existing }
    } else {
        this + item
    }
