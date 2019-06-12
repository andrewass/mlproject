package com.mlproject.mlproject.misc

import weka.core.AttributeStats
import javax.persistence.Entity

class TrainingAttribute(val attributeName : String, val attributeStats : AttributeStats)