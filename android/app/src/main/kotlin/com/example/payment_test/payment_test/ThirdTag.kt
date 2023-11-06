package com.intersoftpos.callpaymentflutterdemo

object ThirdTag {
    /**
     * responseCode
     */
    const val RESPONSE_CODE = "responseCode"

    /**
     * response massage
     */
    const val MESSAGE = "message"

    /**
     * merchantId
     */
    const val MERCHANT_ID = "merchantId"

    /**
     * merchantNam
     */
    const val MERCHANT_NAME = "merchantNam"

    /**
     * terminalId
     */
    const val TERMINAL_ID = "terminalId"

    /**
     * channelId
     */
    const val CHANNEL_ID = "channelId"

    /**
     * operatorNo
     */
    const val OPER = "operatorNo"

    /**
     * transType
     */
    const val TRANS_TYPE = "transType"

    /**
     * Bitmap
     */
    const val Bitmap = "BitmapImage"

    /**
     *
     * / **
     * amount
     */
    const val AMOUNT = "amount"
    const val TRANS_AMOUNT = "transAmount"

    /**
     * cardNo
     */
    const val CARD_NO = "cardNo"

    /**
     * cardSerialNo
     */
    const val CARD_SN = "cardSerialNo"

    /**
     * expDate
     */
    const val EXP_DATE = "expDate"

    /**
     * voucherNo
     */
    const val TRACE_NO = "voucherNo"

    /**
     * batchNo
     */
    const val BATCH_NO = "batchNo"

    /**
     * referenceNo
     */
    const val REFERENCE_NO = "referenceNo"

    /**
     * authCode
     */
    const val AUTH_CODE = "authCode"

    /**
     * balance
     */
    const val BALANCE = "balance"

    /**
     * original VoucherNo
     */
    const val OLD_TRACE_NO = "oriVoucherNo"

    /**
     * original AuthCode
     */
    const val OLD_AUTH_CODE = "oriAuthCode"

    /**
     * original ReferenceNo
     */
    const val OLD_REFERENCE_NO = "oriReferenceNo"

    /**
     * original transaction data  YYMMDDHHMMSS
     */
    const val OLD_TRANS_TIME = "oriTransTime"

    /**
     * transaction data   YYMMDDHHMMSS
     */
    const val TRANS_TIME = "transTime"

    /**
     * external OrderNo
     */
    const val OUT_ORDERNO = "outOrderNo"

    /**
     * 付款码,收银台扫码后，结果通过该TAG传给收单
     * using in scan transaction
     */
    const val PAY_CODE = "payCode"

    /**
     * 扫码订单号,扫码类交易的结果订单号可以放此
     * scan transaction
     */
    const val QR_ORDER = "payOrderNo"

    /**
     * 是否需要主管密码 ,默认显示.  true：显示  false：不显示
     *
     */
    const val IS_OPEN_ADMIN = "isOpenAdminVerify"

    /**
     * 读卡方式 ，
     *
     */
    const val CARD_INPUT_MODE = "cardInputMode"

    /**
     * 备注
     */
    const val REMARKS = "remarks"

    /**
     * 主密钥索引
     */
    const val MAIN_INDEX = "tmkIndex"
    //===========版本2.2.00增加============
    /**
     * 分期数
     */
    const val PERIODS = "periods"

    /**
     * 收单行名称
     */
    const val ACQ_NAME = "acqName"

    /**
     * 收单行编码
     */
    const val ACQ_CODE = "acqCode"

    /**
     * 发卡行名称
     */
    const val IIS_NAME = "iisName"

    /**
     * 发卡行编码
     */
    const val IIS_CODE = "iisCode"

    /**
     * 卡组织
     */
    const val INTER_ORG = "interOrg"

    /**
     * 货币类型
     */
    const val CURRENCY_CODE = "currencyCode"

    /**
     * 银行卡属性
     */
    const val CARD_ATTRIBUTE = "cardAttribute"

    /**
     * 原外部订单号
     */
    const val OLD_OUT_ORDERNO = "oriOutOrderNo"

    /**
     * 闪付凭密
     */
    const val RF_FORCE_PSW = "rfForcePsw"

    /**
     * contact transaction
     */
    const val INSERT_SALE = "insertSale"
    const val JSON_DATA = "JSON_DATA"

    //CR# 15.09.2021
    const val XML_DATA = "XML_DATA" //========================
}
