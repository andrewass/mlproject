package com.mlproject.mlproject.classifier.evaluation

import com.mlproject.mlproject.classifier.classifiers.AbstractClassifierWrapper
import weka.classifiers.Evaluation
import weka.core.Instances
import java.util.*

class CrossValidation(val classifier: AbstractClassifierWrapper, val instances : Instances)
    : AbstractEvaluationWrapper(classifier, instances){


    fun runCrossValidation() : String {
        val evaluation = Evaluation(instances)
        evaluation.crossValidateModel(classifier.classifier, instances, 10, Random(1))
        return evaluation.toSummaryString()
    }
}