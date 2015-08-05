package com.ajaxified.datatable.demo

import grails.converters.JSON
import grails.util.Holders
import groovy.json.JsonSlurper

class EmployeeController {

    def list() {
        Integer totalRecords = getParsedDemoData().size()
        [totalRecords: totalRecords]
    }

    def ajax_fetchList() {
        Map paginationDetails = getPaginationAndSortDetails(params)
        List<Map> employeeInstanceList = getParsedDemoData(), result = employeeInstanceList[paginationDetails.startIndex..paginationDetails.endIndex]
        render([data: getList(result), recordsTotal: employeeInstanceList.size(), recordsFiltered: employeeInstanceList.size()] as JSON)
    }

    private static List getColumns() {
        return ['firstName', 'lastName', 'email', 'age', 'city', 'salary']
    }

    private static List<List> getList(List<Map> result) {
        List valueList = []
        result.each {
            valueList.add([it."${columns[0]}", it."${columns[1]}", it."${columns[2]}", it."${columns[3]}", it."${columns[4]}", it."${columns[5]}"])
        }
        return valueList
    }

    private static Map getPaginationAndSortDetails(Map parameters) {
        Map order = parameters.findAll { it.key.toString().startsWith('order') }
        Integer columnNumber = Integer.parseInt(order.find { it.key.toString().contains('column') }.value.toString()),
                startIndex = Integer.parseInt(parameters.start), endIndex = startIndex + Integer.parseInt(parameters.length)
        String sortOrder = columns[columnNumber] + '_' + order.find { it.key.toString().contains('dir') }.value
        return [startIndex: startIndex, endIndex: endIndex-1, sortOrder: sortOrder]
    }

    private static List<Map> getParsedDemoData() {
        List<Map> list = readAndParseFixture('employees.json').data
        4.times {
            list.addAll(list)
        }
        return list
    }

    private static Map readAndParseFixture(String fileName) {
        File jsonFile = Holders.grailsApplication.mainContext.getResource("classpath:" + "${fileName}").file
        return (new JsonSlurper().parseText(jsonFile.text) as Map)
    }
}
