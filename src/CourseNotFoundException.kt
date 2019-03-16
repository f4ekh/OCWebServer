package com.lutz.bct

data class CourseNotFoundException(var status: Int = 404,
                                   var message: String = "Sorry ! No course were found...")