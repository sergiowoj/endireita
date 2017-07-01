package com.app.endireita

import wslite.rest.ContentType
import wslite.rest.RESTClient

class MockController {

    IssueService issueService

    def installIssueTypes(){
        List<IssueType> issueTypeList = issueService.installIssueTypes()
        render "Issue Types installed. \n ${issueTypeList}"
    }

    def getIssue(){
        String endpoint = "localhost:7272/api/issue?token=" + token
        try {
            def client = new RESTClient(endpoint)
            def response = client.get(
                    accept: ContentType.JSON,
                    connectTimeout: 15000,
                    readTimeout: 30000,
                    followRedirects: false,
                    useCaches: false,
                    sslTrustAllCerts: true )
            if (response.statusCode==200)
                if (response.json.status==0) {
                    Long redeemIdReference = encryptionUrlParams.decode(response.json.redeemIdReference).toString().toLong()
                    redeem.redeemIdReference = redeemIdReference
                    redeem.save(flush:true, failOnError:true)
                    return [status: response.json.status, message: response.json.message,
                            redeemIdReference: redeemIdReference]
                } else {
                    log.error("Error on requestRedeemAuthorization return for " +
                            "store redeem: ${redeem?.id} - " +
                            "message: ${response.json.message}")
                    return [status:2, message:response.json.message, redeemIdReference:0]
                }
            else return [status: response.json.status, message: response.json.message, redeemIdReference :0]
        } catch (Exception e) {
            log.error("Error on requestRedeemAuthorization for " +
                    "store redeem: ${redeem?.id}", e)
            e.printStackTrace()
            return [status:2, message:"Error calling endpoint ${endpoint} ${message:e.getMessage()}", redeemIdReference:0]
        }
    }
}
