package com.twistedgeinc.apps.tiwapa.family


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.twistedgeinc.apps.tiwapa.R
import com.twistedgeinc.apps.tiwapa.TiwapaFirebaseAuth
import com.twistedgeinc.apps.tiwapa.TiwapaPersonFirebaseDA
import com.twistedgeinc.apps.tiwapa.models.TiwapaPerson
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch


class SiblingFragment : Fragment() {

    private var siblings = arrayListOf<TiwapaPerson>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sibling, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val siblingRV = view.findViewById<RecyclerView>(R.id.siblings_RecyclerView)
        val rvLayoutManager = LinearLayoutManager(this.context)

        siblingRV.apply {
            layoutManager = rvLayoutManager
            setHasFixedSize(true)
            adapter = SiblingsAdapter(siblings)
        }

        launch(UI) {

            var querySnapshot = TiwapaPersonFirebaseDA().getAllRelatives(TiwapaFirebaseAuth().userId!!)

            for(docSnapshot in querySnapshot) {
                val relative = docSnapshot.toObject(TiwapaPerson::class.java)
                siblings.add(relative)
            }

            siblingRV.adapter.notifyDataSetChanged()
        }

    }

}
