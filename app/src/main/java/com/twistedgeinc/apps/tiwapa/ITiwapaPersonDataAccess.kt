package com.twistedgeinc.apps.tiwapa

import com.twistedgeinc.apps.tiwapa.models.TiwapaPerson

interface ITiwapaPersonDataAccess {

    fun getAllRelatives(userId: String): ArrayList<TiwapaPerson>

    fun addRelative(tiwapaPerson: TiwapaPerson): Boolean

    fun deleteRelative(tiwapaPerson: TiwapaPerson): Boolean

    fun updateRelative(tiwapaPerson: TiwapaPerson): Boolean

}