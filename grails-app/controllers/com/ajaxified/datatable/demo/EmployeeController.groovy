package com.ajaxified.datatable.demo

import grails.util.Holders
import groovy.json.JsonSlurper

class EmployeeController {

    def list() {
    }

    List<Map> getParsedDemoData() {
        List<Map> list = readAndParseFixture('employees.json').data
        4.times {
            list.addAll(list)
        }
        return list
    }

    Map readAndParseFixture(String fileName) {
        File jsonFile = Holders.grailsApplication.mainContext.getResource("classpath:" + "${fileName}").file
        return (new JsonSlurper().parseText(jsonFile.text) as Map)
    }
}
