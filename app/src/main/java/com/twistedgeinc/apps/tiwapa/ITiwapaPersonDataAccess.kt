package com.twistedgeinc.apps.tiwapa

import android.arch.lifecycle.LiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import com.twistedgeinc.apps.tiwapa.models.TiwapaPerson
import kotlinx.coroutines.experimental.Deferred

interface ITiwapaPersonDataAccess {

    suspend fun getAllRelatives(userId: String): QuerySnapshot

    fun addRelative(userId: String, tiwapaPerson: TiwapaPerson): Boolean

    fun deleteRelative(tiwapaPerson: TiwapaPerson): Boolean

    fun updateRelative(tiwapaPerson: TiwapaPerson): Boolean

}