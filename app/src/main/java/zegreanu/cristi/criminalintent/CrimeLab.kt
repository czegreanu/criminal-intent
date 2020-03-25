package zegreanu.cristi.criminalintent

import java.util.UUID

object CrimeLab {
    var crimes: ArrayList<Crime> = arrayListOf()

    init {
        for (i in 1..100) {
            crimes.add(Crime("Crime #$i", i % 2 == 0, i % 3 == 0))
        }
    }

    operator fun get(id: UUID): Crime? {
        return crimes.find { crime -> crime.id == id }
    }
}
