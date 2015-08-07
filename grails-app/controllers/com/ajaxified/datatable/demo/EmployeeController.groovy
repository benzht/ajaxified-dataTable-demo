package com.ajaxified.datatable.demo

import grails.converters.JSON
import grails.util.Holders
import groovy.json.JsonSlurper

class EmployeeController {

    private static List<Map> readAndParseFixture(String fileName) {
        File jsonFile = Holders.grailsApplication.mainContext.getResource("classpath:" + "${fileName}").file
        return (new JsonSlurper().parseText(jsonFile.text) as Map).data
    }

    def list() {
        [totalRecords: readAndParseFixture('employees.json')?.size()]
    }

    /* method used to fetch records through ajax call */
    def ajax_fetchList() {
        List<Map> employeeList = readAndParseFixture('employees.json')
        employeeList = searchRecords(employeeList, params)
        Map paginationDetails = getPaginationAndSortDetails(params, employeeList.size())
        employeeList = sortList(employeeList, paginationDetails)
        List<Map> result = employeeList ? employeeList[paginationDetails.startIndex..paginationDetails.endIndex] : []
        render([data: getList(result), recordsTotal: employeeList.size(), recordsFiltered: employeeList.size()] as JSON)
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

    private static List<Map> sortList(List<Map> list, Map paginationDetails) {
        if (paginationDetails?.sortOrder) {
            list = list?.sort { it."${paginationDetails.sortBy}" }
            list = (paginationDetails.sortOrder == 'desc') ? list?.reverse() : list
        }
        return list
    }

    private static List<Map> searchRecords(List<Map> list, Map parameters) {
        String searchText = (parameters?."search[value]").toString().trim()
        if (searchText) {
            list = list.findAll { Map temp ->
                temp.any { entry -> entry.value.toString().toLowerCase().contains(searchText.toLowerCase()) }
            }
        }
        return list
    }

    private static Map getPaginationAndSortDetails(Map parameters, Integer total) {
        Map order = parameters.findAll { it.key.toString().startsWith('order') }        // order[0][dir]:asc where 0 is column no and asc is the sortOrder
        Integer columnNumber = Integer.parseInt(order.find { it.key.toString().contains('column') }.value.toString()), // fetch column no
                startIndex = Integer.parseInt(parameters.start), endIndex = (startIndex + Integer.parseInt(parameters.length))
        endIndex = endIndex > total ? total : endIndex // check if endIndex is greater than no of records
        String sortOrder = order.find { it.key.toString().contains('dir') }.value// find sort order - asc or desc

        return [startIndex: startIndex, endIndex: endIndex - 1, sortBy: columns[columnNumber], sortOrder: sortOrder]
    }
}
