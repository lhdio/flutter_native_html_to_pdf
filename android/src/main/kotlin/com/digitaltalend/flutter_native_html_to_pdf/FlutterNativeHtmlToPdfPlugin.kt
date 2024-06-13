package com.digitaltalend.flutter_native_html_to_pdf

import android.content.Context
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

class FlutterNativeHtmlToPdfPlugin : FlutterPlugin, MethodCallHandler {
    private lateinit var channel: MethodChannel
    private lateinit var context: Context

    override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        context = flutterPluginBinding.applicationContext
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "flutter_native_html_to_pdf")
        channel.setMethodCallHandler(this)
    }

    override fun onMethodCall(call: MethodCall, result: Result) {
        if (call.method == "convertHtmlToPdf") {
            convertHtmlToPdf(call, result)
        } else {
            result.notImplemented()
        }
    }

    private fun convertHtmlToPdf(call: MethodCall, result: Result) {
        val htmlFilePath = call.argument<String>("htmlFilePath")

        HtmlToPdfConverter().convert(htmlFilePath!!, context, object : HtmlToPdfConverter.Callback {
            override fun onSuccess(filePath: String) {
                result.success(filePath)
            }

            override fun onFailure() {
                result.error("ERROR", "Unable to convert html to pdf document!", "")
            }
        })
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }
}
