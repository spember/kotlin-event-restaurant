package pember.example.ktrestaurant.app.mappings

import com.fasterxml.jackson.annotation.JsonProperty

class CreateRestaurantMetaCommand(@JsonProperty("name") val name: String)