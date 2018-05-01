package com.twistedgeinc.apps.tiwapa


import com.twistedgeinc.apps.tiwapa.models.RelativeType
import com.twistedgeinc.apps.tiwapa.models.GenderType
import com.twistedgeinc.apps.tiwapa.models.MAX_NO_PARENTS
import com.twistedgeinc.apps.tiwapa.models.TiwapaPerson
import junit.framework.TestCase.assertFalse
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.hamcrest.core.Every.everyItem


class TiwapaPersonTest {
    var kayodeDada = TiwapaPerson()
    var numOfRelativesAdded = 5


    @Before
    fun setUp() {

        val tiwapaDAO = TiwapaPersonFirebaseDA()

        kayodeDada = TiwapaPersonBuilder()
                .withRelative("Oyewole", "Olubayode", "Dada", GenderType.MALE, RelativeType.SIBLING)
                .withRelative("Ayodele", "Olufunmilayo", "Dada", GenderType.FEMALE, RelativeType.PARENT)
                .withRelative("Peter", "Olusola", "Dada", GenderType.MALE, RelativeType.PARENT)
                .withRelative("Dawn", "Michelle", "Beatty", GenderType.FEMALE, RelativeType.SPOUSE)
                .withRelative("Bibilomo", "Kayode", "Dada", GenderType.FEMALE, RelativeType.CHILD)
                .build()

    }

    @After
    fun tearDown() {

    }

    @Test
    fun parentsAddedWithValidData() {

        val tiwapaParents = kayodeDada.getRelatives(RelativeType.PARENT)
        assertThat(tiwapaParents, everyItem(hasProperty("lastName", `is` ("Dada"))))


    }

    @Test
    fun childrenAddedSuccessfully() {
        val children = kayodeDada.getRelatives(RelativeType.CHILD)
        assertThat(children, everyItem(hasProperty("lastName", `is`("Dada"))))
    }


    @Test
    fun allRelativesAdded() {

        val tiwapaRelatives = kayodeDada.getRelatives(null)
        assertThat(tiwapaRelatives, hasSize(numOfRelativesAdded))
    }

    @Test
    fun shouldNotHaveMoreThanTwoParents() {
        val parentNo3 = TiwapaPerson()
        kayodeDada.addRelatives(parentNo3)

        assertFalse(kayodeDada.getRelatives(RelativeType.PARENT).size > MAX_NO_PARENTS )

    }

    @Test
    fun spouseAddedSuccessfully() {
        val tiwapaSpouses = kayodeDada.getRelatives(RelativeType.SPOUSE)
        assertThat(tiwapaSpouses[0].lastName, `is`("Beatty"))
    }

}