package zegreanu.cristi.criminalintent

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import java.util.UUID

class CrimePagerActivity : AppCompatActivity() {
    private lateinit var firstButton: Button
    private lateinit var lastButton: Button
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
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)

                if (position == 0)
                    firstButton.visibility = View.GONE
                else
                    firstButton.visibility = View.VISIBLE

                if (position == crimes.size - 1)
                    lastButton.visibility = View.GONE
                else
                    lastButton.visibility = View.VISIBLE
            }
        })

        firstButton = findViewById(R.id.first_button)
        firstButton.setOnClickListener {
            viewPager.currentItem = 0
        }

        lastButton = findViewById(R.id.last_button)
        lastButton.setOnClickListener {
            viewPager.currentItem = crimes.size - 1
        }
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
