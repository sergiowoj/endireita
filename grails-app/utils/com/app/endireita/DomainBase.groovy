package com.app.endireita
/**
 * Created by sergiowoj on 2/19/17.
 */
abstract class DomainBase implements Serializable{

    Date dateCreated = new Date()
    Date lastUpdated = new Date()
    String createdBy
    String updatedBy

    static constraints = {
        dateCreated nullable: false
        lastUpdated nullable: false
        createdBy size: 1..32, nullable: true
        updatedBy size: 1..32, nullable: true
    }
}
