package jacaceresf.dev.models

import kotlinx.serialization.Serializable

@Serializable
data class Order(val number: String, val contents: List<OrderItem>)

@Serializable
data class OrderItem(val item: String, val amount: Int, val price: Double)

@Serializable
data class OrderTotal(val number: String, val totalPrice: Double)

val orderStorage = listOf(
    Order(
        "2021100201",
        listOf(
            OrderItem("Ham Sandwich", 1, 1.50),
            OrderItem("Water", 3, 1.45),
            OrderItem("Beer", 6, 3.75)
        )
    ),
    Order(
        "2020-04-03-01",
        listOf(
            OrderItem("Cheeseburger", 1, 8.50),
            OrderItem("Water", 2, 1.50),
            OrderItem("Coke", 2, 1.76),
            OrderItem("Ice Cream", 1, 2.35)
        )
    )
)