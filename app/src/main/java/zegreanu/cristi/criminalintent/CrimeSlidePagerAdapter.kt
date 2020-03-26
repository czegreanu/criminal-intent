package zegreanu.cristi.criminalintent

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class CrimeSlidePagerAdapter(var crimes: ArrayList<Crime>, activity: FragmentActivity) :
    FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return crimes.size
    }

    override fun createFragment(position: Int): Fragment {
        return CrimeFragment.newInstance(crimeId = crimes[position].id)
    }
}
