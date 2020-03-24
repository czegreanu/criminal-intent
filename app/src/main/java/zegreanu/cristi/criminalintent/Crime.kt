package zegreanu.cristi.criminalintent

import java.util.Date
import java.util.UUID

class Crime constructor() {
    var id: UUID = UUID.randomUUID()
    var date: Date = Date()
    var title: String = ""
    var solved: Boolean = false

    constructor(title: String, solved: Boolean) : this() {
        this.title = title
        this.solved = solved
    }
}
