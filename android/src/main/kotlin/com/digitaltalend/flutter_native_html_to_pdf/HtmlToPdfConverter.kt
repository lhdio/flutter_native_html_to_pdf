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

        val printManager = applicationContext.getSystemService(Context.PRINT_SERVICE) as android.print.PrintManager

        val printAdapter = webView.createPrintDocumentAdapter(temporaryDocumentName)

        val printAttributes = PrintAttributes.Builder()
            .setMediaSize(PrintAttributes.MediaSize.UNKNOWN_PORTRAIT)
            .setResolution(PrintAttributes.Resolution("pdf", "pdf", 600, 600))
            .setMinMargins(PrintAttributes.Margins.NO_MARGINS)
            .build()

        val pdfPrintAttrs = android.print.PrintAttributes.Builder()
            .setMediaSize(android.print.PrintAttributes.MediaSize.UNKNOWN_PORTRAIT)
            .setResolution(android.print.PrintAttributes.Resolution("pdf", "pdf", 600, 600))
            .setMinMargins(android.print.PrintAttributes.Margins.NO_MARGINS)
            .build()

        val printJob = printManager.print(temporaryDocumentName, printAdapter, pdfPrintAttrs)

        printJob.addPrintJobStateChangeListener(object : android.print.PrintJobStateChangeListener() {
            override fun onPrintJobStateChanged(printJobId: android.print.PrintJobId) {
                val job = printManager.getPrintJob(printJobId)
                when (job?.info?.state) {
                    android.print.PrintJobInfo.STATE_COMPLETED -> {
                        val filePath = "${path.absolutePath}/${job.info.label}"
                        callback.onSuccess(filePath)
                    }
                    android.print.PrintJobInfo.STATE_FAILED -> {
                        callback.onFailure()
                    }
                }
            }
        })
    }
}
    companion object {
        const val temporaryDocumentName = "TemporaryDocumentName"
        const val temporaryFileName = "TemporaryDocumentFile.pdf"
    }
}