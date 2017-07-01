package com.app.endireita

class User extends DomainBase {

    String firstName
    String lastName
    String email

    static constraints = {
        firstName nullable: false, blank: false
        lastName nullable: false, blank: false
        email nullable: false, blank: false

    }
}
