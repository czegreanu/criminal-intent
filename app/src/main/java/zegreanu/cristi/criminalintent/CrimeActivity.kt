package zegreanu.cristi.criminalintent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class CrimeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crime)

        if (supportFragmentManager.findFragmentById(R.id.fragment_container) == null)
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, CrimeFragment())
                .commit()
    }
}
