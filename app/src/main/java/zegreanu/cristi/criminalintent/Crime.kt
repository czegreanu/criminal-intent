package zegreanu.cristi.criminalintent

import java.util.Date
import java.util.UUID

class Crime {
    var id: UUID = UUID.randomUUID()
    var date: Date = Date()
    lateinit var title: String
    var solved: Boolean = false
}
