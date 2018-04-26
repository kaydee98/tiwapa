package com.twistedgeinc.apps.tiwapa

import com.twistedgeinc.apps.tiwapa.models.TiwapaPerson

class TiwapaPersonFirebaseDA: ITiwapaPersonDataAccess {
    override fun getAllRelatives(userId: String): ArrayList<TiwapaPerson> {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return emptyList<TiwapaPerson>() as ArrayList<TiwapaPerson>
    }

    override fun addRelative(tiwapaPerson: TiwapaPerson): Boolean {
        return true
    }

    override fun deleteRelative(tiwapaPerson: TiwapaPerson): Boolean {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return true
    }

    override fun updateRelative(tiwapaPerson: TiwapaPerson): Boolean {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return true
    }
}