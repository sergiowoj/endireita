package com.app.endireita

class IssueService {

    public List<IssueType> installIssueTypes(){
        List<IssueType> issueTypeList = [
                new IssueType(code: 'limpeza', name: 'Limpeza'),
                new IssueType(code: 'conservacao', name: 'Conservação'),
                new IssueType(code: 'vazamento', name: 'Vazamento de água/esgoto'),
                new IssueType(code: 'estacionamento', name: 'Estacionamento irregular'),
                new IssueType(code: 'consertos', name: 'Consertos gerais'),
                new IssueType(code: 'incendio', name: 'Incêndio'),
                new IssueType(code: 'outro', name: 'Outro tipo de chamado')
        ]

        issueTypeList.each {
            it.save()
        }

        return issueTypeList
    }

    public Map createIssue(Map issueInfo){
        Issue issue = new Issue()
        issue.locationAdditionalInfo = issueInfo.locationAdditionalInfo
        issue.description = issueInfo.description
        issue.latitude = issueInfo.latitude.toString()
        issue.longitude = issueInfo.longitude.toString()
        issue.user = User.load(1l)
        issue.issueStatus = IssueStatus.load(1l)
        issue.issueType = IssueType.load(issueInfo.issueTypeId.toLong())

        Map status = [:]
        try{
            issue.save(flush:true, failOnError:true)
            status = ["status": 0]
        } catch (Exception e){
            println e.message
            status =  ["status": 1, "message": "ERROR: Couldn't  save object. ${e.message}"]
        }

        return status
    }

    public List<Map> getIssueTypeMap(){
        List<IssueType> issueTypeList = IssueType.list()

        List<Map> issueTypeMapList = []
        issueTypeList.each { IssueType issueType ->
            issueTypeMapList << ["id": issueType.id, "code": issueType.code, "name": issueType.name, "icon": issueType.icon]
        }

        return issueTypeMapList
    }
}
