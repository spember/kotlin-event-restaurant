package pember.example.ktrestaurant.core.menu

/**
 * Menu Item is a representation of some food item, modelled as a Value Object.
 *
 * Could contain multiple properties (like name, calories, etc)... but not price, as that may change over time on some
 * menu. In this case [code] refers to some short stub or sku for identification
 */
class MenuItem (val name: String, val code: String, val type: FoodType, val description: String?, val calories: Int = 0)