package com.tuncayavci.countryinfo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.tuncayavci.countryinfo.R
import com.tuncayavci.countryinfo.databinding.ListRowBinding
import com.tuncayavci.countryinfo.model.Country
import com.tuncayavci.countryinfo.util.downloadFromUrl
import com.tuncayavci.countryinfo.util.placeHolderProgressBar
import com.tuncayavci.countryinfo.view.FeedFragmentDirections
import kotlinx.android.synthetic.main.list_row.view.*



class CountryAdapter(val countryList : ArrayList<Country>) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() , CountryClickListener  {

    // class CountryViewHolder(var view : View) : RecyclerView.ViewHolder(view)
    class CountryViewHolder(var view : ListRowBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.list_row,parent,false)
        val view = DataBindingUtil.inflate<ListRowBinding>(inflater,R.layout.list_row,parent,false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.view.country = countryList[position]
        holder.view.listener = this

       /* holder.view.name.text = countryList[position].countryName
        holder.view.region.text = countryList[position].countryRegion

        holder.view.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(countryUuid = countryList[position].uuid)
            //action.countryUuid

            Navigation.findNavController(it).navigate(action)
        }

        holder.view.imageView.downloadFromUrl(countryList[position].imageUrl,
            placeHolderProgressBar(holder.view.context)
        )*/
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    fun updateCountryList(newCountryList : List<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

    override fun onCountryClicked(v: View) {
        val uuid = v.countryUuidText.text.toString().toInt()
        val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(uuid)
        Navigation.findNavController(v).navigate(action)
    }
}