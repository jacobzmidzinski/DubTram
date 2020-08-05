package com.jacobzmidzinski.dubtram.data.models

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root


@Root(name = "stopInfo", strict = false)
data class TramsForecastDataModel(
    @field:Attribute(required = false)
    var stop: String = "",
    @field:Element(required = false)
    var message: String = "",
    @field:ElementList(name = "direction", required = false, inline = true)
    var directions: MutableList<Direction> = mutableListOf()
)

data class Direction(
    @field:Attribute(required = false)
    var name: String = "",
    @field:ElementList(name = "tram", required = false, inline = true)
    var trams: MutableList<Tram> = mutableListOf()
)

data class Tram(
    @field:Attribute(required = false)
    var dueMins: String = "",
    @field:Attribute(required = false)
    var destination: String = ""
)

