package com.example.payment_test.payment_test

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.NonNull
import com.intersoftpos.callpaymentflutterdemo.Consts
import com.intersoftpos.callpaymentflutterdemo.ThirdTag
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodChannel
import org.jetbrains.annotations.Nullable
import java.io.ByteArrayOutputStream

class MainActivity: FlutterActivity() {

    private val METHOD_CHANNEL_NAME = "com.mada.intersoft/method"


    private var methodChannel: MethodChannel? = null
    //private  var xx = MethodChannel.Result? null
    private var xmlResult = "test";
    private var isPrinting = "false";
    private var methodName ="";
    var mIsActivityDone: Boolean = true

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        //Setup Channels
        setupChannels(this,flutterEngine.dartExecutor.binaryMessenger)

    }

    override fun onDestroy() {
        teardownChannels()
        super.onDestroy()
    }

    private fun setupChannels(context: Context, messenger: BinaryMessenger){

        methodChannel = MethodChannel(messenger, METHOD_CHANNEL_NAME)
        methodChannel!!.setMethodCallHandler{
                call,result ->
            if (call.method.contains("Sale")) {
                methodName="Sale";
                val Amount : String? = call.argument("Amount")
                onViewClicked(Amount)
                Log.d("DEBUG", "zzzthis run after finish")

                result.success(xmlResult)


            }
            else if (call.method.contains("Print"))
            {
                methodName="Print";
                val img : ByteArray? = call.argument("img")

                if (img != null) {
                    printImage(img)

                };
                result.success(isPrinting);
            }
            else {
                result.notImplemented()
            }
        }


    }

    private fun teardownChannels(){
        methodChannel!!.setMethodCallHandler(null)

    }

    fun printImage(img:ByteArray) {
        var imgReceipt: Bitmap? = null
        imgReceipt = BitmapFactory.decodeByteArray(img, 0, img.size)

        val intent = Intent()
        intent.setAction(Intent.ACTION_SEND)
        intent.setAction(Consts.CARD_ACTION)
        intent.putExtra(ThirdTag.CHANNEL_ID, "acquire")
        intent.putExtra(ThirdTag.TRANS_TYPE, 9000)
        intent.putExtra(ThirdTag.OUT_ORDERNO, "printImage")
        intent.putExtra(ThirdTag.IS_OPEN_ADMIN, 1)

        val uri = getImageUri(applicationContext, imgReceipt)
        intent.putExtra("imagePath", uri.toString())
        try {
            startActivityForResult(intent, 12)

        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }

    }

    fun getImageUri(inContext: Context, inImage: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path =
            MediaStore.Images.Media.insertImage(inContext.contentResolver, inImage, "Title", null)
        return Uri.parse(path)
    }

    fun onViewClicked( amount: String?)  {
        val intent = Intent()
        intent.setPackage(Consts.PACKAGE)
        intent.action = Consts.CARD_ACTION
        intent.putExtra(ThirdTag.CHANNEL_ID, "acquire")
        intent.putExtra(ThirdTag.TRANS_TYPE, "2".toInt())
        //CR# 21.09.2021
        // intent.putExtra(ThirdTag.OUT_ORDERNO, tvOutOrderNo.getText().toString())
        //=================================================================
        if(amount != null)
            intent.putExtra(ThirdTag.AMOUNT, amount.toLong())
        intent.putExtra(ThirdTag.INSERT_SALE, true)
        intent.putExtra(ThirdTag.RF_FORCE_PSW, true)
        startActivityForResult(intent, 12)



    }
    override fun onActivityResult(requestCode: Int, resultCode: Int,data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (methodName == "Sale")
        {
            data.getStringExtra("123")
            xmlResult = data.getStringExtra("JSON_DATA")!!
            PushResult(xmlResult)
        }
        else if (methodName== "Print")
        {
            isPrinting = "true";
            PushResult(isPrinting)
        }



    }

    fun PushResult(result: String) {
        methodChannel?.invokeMethod("showResult", result)
    }

}
