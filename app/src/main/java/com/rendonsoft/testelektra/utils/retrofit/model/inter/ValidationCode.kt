package com.rendonsoft.testelektra.utils.retrofit.model.inter

interface ValidationCode<in T> {
    fun executeValidation(response : T)
}