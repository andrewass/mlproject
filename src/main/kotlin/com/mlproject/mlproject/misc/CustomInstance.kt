package com.mlproject.mlproject.misc

import weka.core.Instance

class CustomInstance{
    val attributeNameList = mutableListOf<String>()
    val attributeValueList = mutableListOf<String>()

    fun addAttribute(name : String, value : String){
        attributeNameList.add(name)
        attributeValueList.add(value)
    }
}