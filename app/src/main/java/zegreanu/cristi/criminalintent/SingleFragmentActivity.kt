package zegreanu.cristi.criminalintent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class SingleFragmentActivity : AppCompatActivity() {
    protected abstract fun createFragment(): Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_fragment)

        if (supportFragmentManager.findFragmentById(R.id.fragment_container) == null)
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, createFragment())
                .commit()
    }
}
