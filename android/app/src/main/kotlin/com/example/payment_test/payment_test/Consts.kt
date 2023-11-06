package com.intersoftpos.callpaymentflutterdemo

object Consts {
    /**
     * payment application package name
     */
    const val PACKAGE = "com.intersoft.acquire.mada"

    /**
     * service  action
     */
    const val SERVICE_ACTION = "android.intent.action.intersoft.PAYMENT.SERVICE"

    /** bank aquire ，action */
    const val CARD_ACTION = "android.intent.action.intersoft.PAYMENT"

    /** union pay scan ，action */
    const val UNIONPAY_ACTION = "android.intent.action.intersoft.PAYMENT_UNION_SCAN"

    /**
     * installment
     */
    const val INSTALLMENT_ACTION = "android.intent.action.intersoft.PAYMENT_INSTALLMENT"
}