package com.helixios.kooassignment.data

class MetaModel {
    var pagination :PageInfo?=null

    class PageInfo {
        var total :Int?=0
        var pages :Int?=0
        var page :Int?=0
        var limit :Int?=0
        var links :LinkInfo?=null

        class LinkInfo {
            var previous :String?=null
            var current :String?=null
            var next :String?=null
        }
    }
}