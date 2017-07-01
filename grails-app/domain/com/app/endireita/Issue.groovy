package com.app.endireita

class Issue extends DomainBase {

    String locationAdditionalInfo
    String description
    User user

    String latitude
    String longitude

    Double likes = 0
    Double views = 0

    IssueStatus issueStatus
    IssueType issueType

    static constraints = {
        locationAdditionalInfo size: 1..64, nullable: false, blank: false
        description size: 1..500, nullable: false, blank: false

        user nullable: false
        latitude nullable: false, blank: false
        longitude nullable: false, blank: false

        likes nullable: false
        views nullable: false

        issueStatus nullable:false
        issueType nullable:false
    }
}
