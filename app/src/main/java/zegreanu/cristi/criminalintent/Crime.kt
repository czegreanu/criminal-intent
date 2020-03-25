package zegreanu.cristi.criminalintent

import java.util.Date
import java.util.UUID

class Crime constructor() {
    var id: UUID = UUID.randomUUID()
    var date: Date = Date()
    var title: String = ""
    var solved: Boolean = false
    var requirePolice: Boolean = false

    constructor(title: String, solved: Boolean, requirePolice: Boolean) : this() {
        this.title = title
        this.solved = solved
        this.requirePolice = requirePolice
    }
}
