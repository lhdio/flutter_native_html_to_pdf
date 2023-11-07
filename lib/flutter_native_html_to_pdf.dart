
import 'flutter_native_html_to_pdf_platform_interface.dart';

class FlutterNativeHtmlToPdf {
  Future<String?> getPlatformVersion() {
    return FlutterNativeHtmlToPdfPlatform.instance.getPlatformVersion();
  }
}
