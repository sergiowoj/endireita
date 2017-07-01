package com.app.endireita

class IssueStatus {

    String code
    String name

    static constraints = {
        code size: 1..2, nullable: false, blank: false
        name size: 1..32, nullable: false, blank: false
    }
}
