package zegreanu.cristi.criminalintent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CrimeListFragment : Fragment() {
    private lateinit var crimeRecyclerView: RecyclerView
    private lateinit var adapter: CrimeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let { }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_crime_list, container, false)

        crimeRecyclerView = rootView.findViewById(R.id.crime_recycler_view)
        crimeRecyclerView.layoutManager = LinearLayoutManager(activity)

        updateUI()

        return rootView
    }

    private fun updateUI() {
        val crimes = CrimeLab.crimes
        adapter = CrimeAdapter(crimes)
        crimeRecyclerView.adapter = adapter
    }

    private class CrimeViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private var titleTextView: TextView = itemView.findViewById(R.id.crime_title)
        private var dateTextView: TextView = itemView.findViewById(R.id.crime_date)
        private lateinit var crime: Crime

        init {
            itemView.setOnClickListener {
                Toast.makeText(
                    itemView.context,
                    crime.title + "CLICKED",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        fun bind(crimeItem: Crime) {
            crime = crimeItem
            titleTextView.text = crime.title
            dateTextView.text = crime.date.toString()
        }
    }

    private class CrimeAdapter(private val crimes: List<Crime>) :
        RecyclerView.Adapter<CrimeViewHolder>() {
        val requirePoliceViewType = 0
        val notRequirePoliceViewType = 1

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeViewHolder {
            val itemView =
                if (viewType == requirePoliceViewType)
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.list_item_crime_police, parent, false)
                else
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.list_item_crime, parent, false)

            return CrimeViewHolder(itemView)
        }

        override fun getItemCount(): Int = crimes.size

        override fun onBindViewHolder(holder: CrimeViewHolder, position: Int) {
            holder.bind(crimes[position])
        }

        override fun getItemViewType(position: Int): Int {
            return if (crimes[position].requirePolice) requirePoliceViewType else notRequirePoliceViewType
        }
    }
}
