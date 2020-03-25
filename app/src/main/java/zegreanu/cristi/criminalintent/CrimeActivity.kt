package zegreanu.cristi.criminalintent

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import java.util.UUID

class CrimeActivity : SingleFragmentActivity() {
    override fun createFragment(): Fragment {
        return CrimeFragment.newInstance(intent.getSerializableExtra(EXTRA_CRIME_ID) as UUID)
    }

    companion object {
        const val EXTRA_CRIME_ID = "zegreanu.cristi.criminalintent.crime_id"

        fun newIntent(packageContext: Context, crimeId: UUID): Intent {
            return Intent(packageContext, CrimeActivity::class.java).putExtra(
                EXTRA_CRIME_ID,
                crimeId
            )
        }
    }
}
