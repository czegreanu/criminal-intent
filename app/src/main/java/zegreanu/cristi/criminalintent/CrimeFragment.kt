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
import java.util.UUID

class CrimeFragment : Fragment() {
    private lateinit var crime: Crime
    private lateinit var titleField: EditText
    private lateinit var dateButton: Button
    private lateinit var solvedCheckBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val crimeId = arguments?.getSerializable(ARG_CRIME_ID)
        crime = CrimeLab[crimeId as UUID] ?: Crime()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_crime, container, false)

        titleField = rootView.findViewById(R.id.crime_title)
        titleField.apply {
            setText(crime.title)
            doOnTextChanged { text, _, _, _ -> crime.title = text.toString() }
        }

        dateButton = rootView.findViewById(R.id.crime_date)
        dateButton.apply {
            text = crime.date.toString()
            isEnabled = false
        }

        solvedCheckBox = rootView.findViewById(R.id.crime_solved)
        solvedCheckBox.apply {
            isChecked = crime.solved
            setOnCheckedChangeListener { _, isChecked -> crime.solved = isChecked }
        }

        return rootView
    }

    companion object {
        const val ARG_CRIME_ID = "zegreanu.cristi.criminalintent.crime_id"

        fun newInstance(crimeId: UUID): CrimeFragment {
            return CrimeFragment().apply {
                arguments = Bundle().apply { putSerializable(ARG_CRIME_ID, crimeId) }
            }
        }
    }
}
