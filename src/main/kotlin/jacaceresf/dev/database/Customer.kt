package jacaceresf.dev.database

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object Customers : Table() {
    val id: Column<String> = varchar("id", 128)
    val firstName: Column<String> = varchar("firstname", 128)
    val lastName: Column<String> = varchar("lastname", 128)
    val email: Column<String> = varchar("email", 128)
}

fun toCustomer(row: ResultRow): Customer {
    return Customer(
        id = row[Customers.id],
        firstName = row[Customers.firstName],
        lastName = row[Customers.lastName],
        email = row[Customers.email]
    )
}

@Serializable
data class Customer(val id: String, val firstName: String, val lastName: String, val email: String)