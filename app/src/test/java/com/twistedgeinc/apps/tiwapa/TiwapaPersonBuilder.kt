package com.twistedgeinc.apps.tiwapa

import com.twistedgeinc.apps.tiwapa.models.GenderType
import com.twistedgeinc.apps.tiwapa.models.RelativeType
import com.twistedgeinc.apps.tiwapa.models.TiwapaPerson


class TiwapaPersonBuilder {

    private var tiwapaPerson = TiwapaPerson()

    fun TiwapaPersonBuilder() {
        tiwapaPerson = TiwapaPerson("Oluwatoyin", "Olukayode", "Dada", GenderType.MALE, RelativeType.SELF)
    }

    fun withRelative(firstName: String, middleName: String, lastName: String, gender: GenderType, relativeType: RelativeType): TiwapaPersonBuilder {
        var relative = TiwapaPerson()
        relative.firstName = firstName
        relative.middleName = middleName
        relative.lastName = lastName
        relative.gender = gender
        relative.relativeType = relativeType
        tiwapaPerson.addRelatives( relative)
       return this
    }

    fun build(): TiwapaPerson {
        return tiwapaPerson
    }
}