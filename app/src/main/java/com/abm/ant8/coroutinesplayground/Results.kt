package com.abm.ant8.coroutinesplayground

inline class RallyResult(val topTen: List<CrewResult>)
inline class DriverChampionshipsStandings(val topTen: List<DriverResult>)

data class CrewResult(val driver: Driver, val codriver: Codriver)
data class DriverResult(val driver: Driver, val points: Int)

inline class Driver(val name: String)
inline class Codriver(val name: String)