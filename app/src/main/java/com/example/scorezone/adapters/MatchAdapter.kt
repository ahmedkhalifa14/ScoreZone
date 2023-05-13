package com.example.scorezone.adapters

import android.graphics.drawable.PictureDrawable
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.scorezone.R
import com.example.scorezone.databinding.MatchLayoutItemBinding
import com.example.scorezone.helper.extractDateTimeLocal
import com.example.scorezone.models.Matche
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class MatchAdapter() : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Matche>() {
        override fun areContentsTheSame(oldItem: Matche, newItem: Matche): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }


        override fun areItemsTheSame(oldItem: Matche, newItem: Matche): Boolean {
            return oldItem.id == newItem.id
        }

    }
     val differ = AsyncListDiffer(this, diffCallback)

    inner class MatchViewHolder(private val binding: MatchLayoutItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bindData(match: Matche) {
            binding.root.apply {
              //  Glide.with(context).load(match.homeTeam.crest).into(binding.homeTeamImg)
             //   Glide.with(context).load(match.awayTeam.crest).into(binding.awayTeamImg)

//                Glide.with(context)
//                    .`as`(PictureDrawable::class.java)
//                    .error(R.drawable.cup)
//                    .load(match.homeTeam.crest)
//                    .listener(SvgSoftwareLayerSetter())
//                    .into(binding.homeTeamImg)
//
//                // Load away team crest
//                Glide.with(context)
//                    .`as`(PictureDrawable::class.java)
//                    .error(R.drawable.cup)
//                    .load(match.awayTeam.crest)
//                    .listener(SvgSoftwareLayerSetter())
//                    .into(binding.awayTeamImg)

                Glide.with(context)
                    .load(match.homeTeam.crest)
                    .transition(DrawableTransitionOptions.withCrossFade())
                 //   .placeholder(R.drawable.placeholder_image)
                   // .error(R.drawable.error_image)
                    .into(binding.homeTeamImg)

                Glide.with(context)
                    .load(match.awayTeam.crest)
                    .transition(DrawableTransitionOptions.withCrossFade())
                // .placeholder(R.drawable.placeholder_image)
                   // .error(R.drawable.error_image)
                    .into(binding.awayTeamImg)

                binding.homeTeamTv.text = match.homeTeam.shortName
                binding.awayTeamTv.text = match.awayTeam.shortName
                val matchTime = extractDateTimeLocal(match.utcDate.toString())
                binding.utcDateTv.text = matchTime.second

            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val binding = MatchLayoutItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MatchViewHolder((binding))
    }

    override fun getItemCount(): Int = differ.currentList.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val match = differ.currentList[position]
        holder.apply {
            bindData(match)
        }
    }

}