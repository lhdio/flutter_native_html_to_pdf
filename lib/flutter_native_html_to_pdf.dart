import 'dart:io';

import 'flutter_native_html_to_pdf_platform_interface.dart';

class FlutterNativeHtmlToPdf {

  Future<File?> convertHtmlToPdf(
      {required String html,  required String targetDirectory, required String targetName}) async {
    return FlutterNativeHtmlToPdfPlatform.instance.convertHtmlToPdf(
      html: html,
      targetDirectory: targetDirectory,
      targetName: targetName,
    );
  }


}
