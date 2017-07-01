package com.app.endireita

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import grails.converters.JSON
import groovy.json.*

class ApiController {

    IssueService issueService

    def getIssue(){

    }

    def createIssue(){
        Map jsonObject = request.JSON

        Map serviceReturn
        if(request.JSON){
            serviceReturn = issueService.createIssue(jsonObject.issue)
        } else {
            serviceReturn = ["status": 2, "message": "ERROR: Missing request.JSON string."]
        }

        render serviceReturn as JSON
    }

    def issueTypeList(){
        Gson gSon = loadGson()
        render gSon.toJson(issueService.getIssueTypeMap())
    }

    public Gson loadGson() {
        Gson gson = new GsonBuilder()
                .disableHtmlEscaping()
                .setPrettyPrinting()
                .serializeNulls()
                .registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY)
                .setDateFormat("yyyy-MM-dd").excludeFieldsWithoutExposeAnnotation()
                .create();
        return gson
    }

}
