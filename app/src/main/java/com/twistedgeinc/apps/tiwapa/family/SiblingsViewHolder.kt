package com.twistedgeinc.apps.tiwapa.family

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.twistedgeinc.apps.tiwapa.R
import com.twistedgeinc.apps.tiwapa.models.TiwapaPerson


class SiblingsViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView) {

    fun bindDataToView(sibling: TiwapaPerson) {
        itemView.findViewById<TextView>(R.id.firstName_TextView).text = "${sibling.firstName} ${sibling.lastName}"
        itemView.findViewById<TextView>(R.id.date_of_birth_TextView).text = sibling.gender.toString()

    }

}
