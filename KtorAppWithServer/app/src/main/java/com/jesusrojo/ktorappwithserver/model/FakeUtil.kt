package com.jesusrojo.ktorappwithserver.model

class FakeUtil {

    companion object{

        fun getFakeJsonRawData(): String {
            return """{
                        "id": 0,
                        "title": "title0",
                        "description": "description0"
                    }"""
        }

        fun getFakeJsonListRawDatas(): String {
            return """{
                        "idRawData": 1,
                        "title": "title1",
                        "description": "description1"
                    }
                    {
                        "idRawData": 2,
                        "title": "title2",
                        "description": "description2"
                    }"""
        }

        fun getFakeResponse(): Response {
            val items = getFakeRawData()
            return Response("OK", items, false
            )
        }

        private fun getFakeRawData(): List<RawData> {
            return listOf(
                RawData(10, "title10", "description10"),
                RawData(11, "title11", "description11")
            )
        }
    }
}