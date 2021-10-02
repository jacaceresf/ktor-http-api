package jacaceresf.dev.services

import jacaceresf.dev.database.Customer
import jacaceresf.dev.database.Customers
import jacaceresf.dev.database.DatabaseFactory
import jacaceresf.dev.database.toCustomer
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class CustomerService {

    suspend fun getAllCustomers(): List<Customer> = DatabaseFactory.dbQuery {
        Customers.selectAll().map { toCustomer(it) }
    }

    suspend fun getCustomerById(id: String): Customer? = DatabaseFactory.dbQuery {
        Customers.select { (Customers.id eq id) }
            .map { toCustomer(it) }
            .singleOrNull()
    }

}