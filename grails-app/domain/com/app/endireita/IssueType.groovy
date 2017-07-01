package com.app.endireita

class IssueType {

    String name
    String code
    String icon

    static constraints = {
        name nullable: false, blank: false
        code nullable: false, blank: false
        icon nullable: true
    }
}
