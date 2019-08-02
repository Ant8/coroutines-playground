package com.abm.ant8.coroutinesplayground

inline class RallyResult(val topTen: List<CrewResult>)

data class CrewResult(val driver: Driver, val codriver: Codriver)
inline class Driver(val name: String)
inline class Codriver(val name: String)

inline class DriverChampionshipsStandings(val topTen: List<DriverResult>)

data class DriverResult(val driver: Driver, val points: Int)