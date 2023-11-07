import 'dart:io';

import 'file_utils.dart';
import 'flutter_native_html_to_pdf_platform_interface.dart';

class FlutterNativeHtmlToPdf {
  Future<String?> getPlatformVersion() {
    return FlutterNativeHtmlToPdfPlatform.instance.getPlatformVersion();
  }

  Future<File?> convertHtmlToPdf(
      {required String html,  required String targetDirectory, required String targetName}) async {
    return FlutterNativeHtmlToPdfPlatform.instance.convertHtmlToPdf(
      html: html,
      targetDirectory: targetDirectory,
      targetName: targetName,
    );
  }


}
