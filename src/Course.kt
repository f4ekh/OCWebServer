package com.lutz.bct

data class Course(var id: Int,
                  var title: String,
                  var level: Int = 1,
                  var isActive: Boolean = false)