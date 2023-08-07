package com.example.mdatepicker1

class User {
    fun setFirst_name(string: String?) {
        TODO("Not yet implemented")
    }

    fun setid(int: Number) {
        TODO("Not yet implemented")
    }

    fun setLast_name(string: String?) {
        TODO("Not yet implemented")
    }

    fun setGender(string: String) {

    }

    fun setEmail(string: String?) {
        TODO("Not yet implemented")
    }

    fun setDate_of_birth(string: String?) {
        TODO("Not yet implemented")
    }

    fun setPassword(string: String?) {
        TODO("Not yet implemented")
    }

    fun setSignup_date(string: String?) {
        TODO("Not yet implemented")
    }

    var id = 0
    var first_name: String? = null
    var last_name: String? = null
    var gender: String? = null
    var email: String? = null
    var date_of_birth: String? = null
    var password: String? = null
    var signup_date: String? = null

    constructor() {}
    constructor(
        first_name: String?, last_name: String?, gender: String?, email: String?,
        date_of_birth: String?, password: String?, sign_up_date: String?,
    ) {
        this.first_name = first_name
        this.last_name = last_name
        this.gender = gender
        this.email = email
        this.date_of_birth = date_of_birth
        this.password = password
        signup_date = sign_up_date
    }

    constructor(
        id: Int, first_name: String?, last_name: String?, gender: String?, email: String?,
        date_of_birth: String?, password: String?, sign_up_date: String?,
    ) {
        this.id = id
        this.first_name = first_name
        this.last_name = last_name
        this.gender = gender
        this.email = email
        this.date_of_birth = date_of_birth
        this.password = password
        signup_date = sign_up_date
    }
}