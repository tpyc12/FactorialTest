package com.myhome.android.factorialtest

sealed class State

object Error : State()
object Progress : State()
class Result(val factorial: String): State()