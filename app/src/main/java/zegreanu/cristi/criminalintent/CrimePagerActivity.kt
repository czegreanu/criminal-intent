package zegreanu.cristi.criminalintent

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import java.util.UUID

class CrimePagerActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private var crimes = arrayListOf<Crime>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crime_pager)

        viewPager = findViewById(R.id.crime_view_pager)
        val crimeId = intent.getUUID()
        crimes = CrimeLab.crimes

        viewPager.adapter = CrimeSlidePagerAdapter(crimes, this)
        viewPager.currentItem = crimes.indexOf(CrimeLab[crimeId])
    }

    companion object {
        const val EXTRA_CRIME_ID = "zegreanu.cristi.criminalintent.crime_id"

        fun newIntent(packageContext: Context, crimeId: UUID): Intent {
            return Intent(packageContext, CrimePagerActivity::class.java).putExtra(
                EXTRA_CRIME_ID,
                crimeId
            )
        }
    }
}

private fun Intent.getUUID(): UUID {
    return getSerializableExtra(CrimePagerActivity.EXTRA_CRIME_ID) as UUID
}
