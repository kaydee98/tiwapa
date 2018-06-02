package com.twistedgeinc.apps.tiwapa.family

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.twistedgeinc.apps.tiwapa.R
import com.twistedgeinc.apps.tiwapa.models.TiwapaPerson

class SiblingsAdapter(siblings: ArrayList<TiwapaPerson>) : RecyclerView.Adapter<SiblingsViewHolder>() {
    private lateinit var context: Context
    private val _siblings = siblings

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): SiblingsViewHolder {
        context = viewGroup.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.fragment_sibling_cardview, viewGroup, false)

        return SiblingsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return _siblings.size
    }

    override fun onBindViewHolder(siblingsViewHolder: SiblingsViewHolder, position: Int) {
        siblingsViewHolder.bindDataToView(_siblings[position])

    }
}