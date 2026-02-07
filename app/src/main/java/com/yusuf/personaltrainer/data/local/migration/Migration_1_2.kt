package com.yusuf.personaltrainer.data.local.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Example: if you changed phoneNumber type or added column
        // For now, leave empty if no manual SQL needed
    }
}
