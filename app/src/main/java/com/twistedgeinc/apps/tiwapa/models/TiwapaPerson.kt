package com.twistedgeinc.apps.tiwapa.models

import com.twistedgeinc.apps.tiwapa.ITiwapaPersonDataAccess
import java.util.*
import kotlin.collections.ArrayList

enum class RelativeType {
    PARENT,
    SIBLING,
    CHILD,
    SPOUSE,
    SELF
}

enum class GenderType {
    MALE,
    FEMALE
}

const val MAX_NO_PARENTS = 2

open class TiwapaPerson() {

    private var countOfParents = 0
    private val relatives = mutableListOf<TiwapaPerson>()

    private var _firstName = ""
    private var _lastName = ""
    private var _middleName = ""
    private var dateOfBirth: Date = Date()
    private var dateOfDeath: Date = Date()
    private var _relativeType = RelativeType.SIBLING
    private var _gender = GenderType.FEMALE

    var firstName: String
        get() = _firstName
        set(firstNameValue) { this._firstName = firstNameValue }

    var middleName: String
        get() = _middleName
        set(_middleNameValue) { this._middleName = _middleNameValue}

    var lastName: String
        get() = _lastName
        set(_lastNameValue) { this._lastName = _lastNameValue}

    var relativeType: RelativeType
        get() = _relativeType
        set(_familyMemberTypeValue) { this._relativeType = _familyMemberTypeValue}

    var gender: GenderType
        get() = _gender
        set(_gender) {  this._gender = _gender
            //throw invalidParameter Error
        }

    constructor(firstName: String, middleName: String, lastName: String, gender: GenderType, relativeType: RelativeType) : this() {
        this.firstName = firstName
        this.middleName = middleName
        this.lastName = lastName
        this.gender = gender
        this.relativeType = relativeType
    }

    fun addRelatives(familyMember: TiwapaPerson) {

        if (familyMember.relativeType == RelativeType.PARENT && countOfParents >= MAX_NO_PARENTS)
            return

        if (familyMember.relativeType == RelativeType.PARENT) {
            countOfParents += 1
        }

        relatives.add(familyMember)

    }


    fun getRelatives(relativeType: RelativeType?): MutableList<TiwapaPerson> {

        return when(relativeType) {

            RelativeType.PARENT -> relatives.filter { it.relativeType == RelativeType.PARENT } as ArrayList<TiwapaPerson>
            RelativeType.CHILD -> relatives.filter { it.relativeType == RelativeType.CHILD } as ArrayList<TiwapaPerson>
            RelativeType.SPOUSE -> relatives.filter { it.relativeType == RelativeType.SPOUSE } as ArrayList<TiwapaPerson>
            RelativeType.SIBLING -> relatives.filter { it.relativeType == RelativeType.SIBLING } as ArrayList<TiwapaPerson>

            else -> relatives
        }
    }

    fun saveRelative(tiwapaPersonDao: ITiwapaPersonDataAccess) {

            //tiwapaPersonDao.addRelative(userId = )
    }

}
