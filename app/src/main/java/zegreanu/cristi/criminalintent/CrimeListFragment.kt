package zegreanu.cristi.criminalintent

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CrimeListFragment : Fragment(), OnItemClickListener {
    private lateinit var crimeRecyclerView: RecyclerView
    private lateinit var adapter: CrimeAdapter
    var positionClicked: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_crime_list, container, false)

        crimeRecyclerView = rootView.findViewById(R.id.crime_recycler_view)
        crimeRecyclerView.layoutManager = LinearLayoutManager(activity)
        crimeRecyclerView.adapter = CrimeAdapter(CrimeLab.crimes, this).also { adapter = it }

        return rootView
    }

    override fun onResume() {
        super.onResume()

        adapter.notifyItemChanged(positionClicked)
    }

    override fun onItemClicked(crime: Crime, position: Int) {
        startActivity(activity?.let { CrimeActivity.newIntent(it, crimeId = crime.id) })
        positionClicked = position
    }

    private class CrimeViewHolder(itemView: View, context: Context) :
        RecyclerView.ViewHolder(itemView) {

        private var titleTextView: TextView = itemView.findViewById(R.id.crime_title)
        private var dateTextView: TextView = itemView.findViewById(R.id.crime_date)
        private var solvedImageView: ImageView = itemView.findViewById(R.id.crime_solved)
        private lateinit var crime: Crime

        fun bind(crimeItem: Crime, clickListener: OnItemClickListener) {
            crime = crimeItem
            titleTextView.text = crime.title
            dateTextView.text = crime.date.toString()
            solvedImageView.visibility = if (crime.solved) View.VISIBLE else View.GONE
            itemView.setOnClickListener {
                clickListener.onItemClicked(crime, adapterPosition)
            }
        }
    }

    private class CrimeAdapter(
        private val crimes: List<Crime>,
        val itemClickListener: OnItemClickListener
    ) :
        RecyclerView.Adapter<CrimeViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeViewHolder {
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.list_item_crime, parent, false)
            return CrimeViewHolder(itemView, parent.context)
        }

        override fun getItemCount(): Int = crimes.size

        override fun onBindViewHolder(holder: CrimeViewHolder, position: Int) {
            holder.bind(crimes[position], itemClickListener)
        }
    }
}

interface OnItemClickListener {
    fun onItemClicked(crime: Crime, position: Int)
}
