package com.mahmoudalyudeen.ambrosia.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations.map
import com.mahmoudalyudeen.ambrosia.db.DatabaseEntry
import com.mahmoudalyudeen.ambrosia.db.FoodDatabase
import com.mahmoudalyudeen.ambrosia.db.asDomainEntries
import com.mahmoudalyudeen.ambrosia.domain.Entry

class EntryRepository(private val foodDatabase: FoodDatabase) {

    val entries: LiveData<List<Entry>> = map(foodDatabase.entryDao.getEntries()) { it.asDomainEntries() }

    suspend fun insertEntry(entry: DatabaseEntry) = foodDatabase.entryDao.insertEntry(entry)
}