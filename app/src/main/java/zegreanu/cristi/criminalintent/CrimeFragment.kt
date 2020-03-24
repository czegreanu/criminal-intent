package zegreanu.cristi.criminalintent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment

class CrimeFragment : Fragment() {
    private lateinit var crime: Crime
    private lateinit var titleField: EditText
    private lateinit var dateButton: Button
    private lateinit var solvedCheckBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        crime = Crime()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_crime, container, false)

        titleField = rootView.findViewById(R.id.crime_title)
        titleField.apply {
            // Other to be added
            doOnTextChanged { text, _, _, _ -> crime.title = text.toString() }
        }

        dateButton = rootView.findViewById(R.id.crime_date)
        dateButton.apply {
            text = crime.date.toString()
            isEnabled = false
        }

        solvedCheckBox = rootView.findViewById(R.id.crime_solved)
        solvedCheckBox.setOnCheckedChangeListener { _, isChecked -> crime.solved = isChecked }

        return rootView
    }
}
