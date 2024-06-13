package com.digitaltalend.flutter_native_html_to_pdf

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.print.PdfPrinter
import android.print.PrintAttributes
import android.webkit.WebView
import android.webkit.WebViewClient

import java.io.File


class HtmlToPdfConverter {

    interface Callback {
        fun onSuccess(filePath: String)
        fun onFailure()
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun convert(filePath: String, applicationContext: Context, callback: Callback) {
        val webView = WebView(applicationContext)
        val htmlContent = File(filePath).readText(Charsets.UTF_8)
        webView.settings.javaScriptEnabled = true
        webView.settings.javaScriptCanOpenWindowsAutomatically = true
        webView.settings.allowFileAccess = true
        webView.loadDataWithBaseURL(null, htmlContent, "text/HTML", "UTF-8", null)
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                createPdfFromWebView(webView, applicationContext, callback)
            }
        }
    }

fun createPdfFromWebView(webView: WebView, applicationContext: Context, callback: Callback) {
    val path = applicationContext.filesDir
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

        val printDocumentAdapter = webView.createPrintDocumentAdapter(temporaryDocumentName)

        printDocumentAdapter.onLayout(null, null, null, object : PrintDocumentAdapter.LayoutResultCallback() {
            override fun onLayoutFinished(info: PrintDocumentInfo, changed: Boolean) {
                printDocumentAdapter.onWrite(
                    arrayOf(PageRange.ALL_PAGES),
                    getOutputFile(path, temporaryFileName),
                    null,
                    object : PrintDocumentAdapter.WriteResultCallback() {
                        override fun onWriteFinished(pages: Array<PageRange>) {
                            if (pages.isNotEmpty()) {
                                val filePath = File(path, temporaryFileName).absolutePath
                                callback.onSuccess(filePath)
                            } else {
                                callback.onFailure()
                            }
                        }
                    })
            }
        }, null)
    }
}

private fun getOutputFile(path: File, fileName: String): ParcelFileDescriptor {
    val file = File(path, fileName)
    return ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_WRITE)
}
    companion object {
        const val temporaryDocumentName = "TemporaryDocumentName"
        const val temporaryFileName = "TemporaryDocumentFile.pdf"
    }
}