import 'package:flutter_test/flutter_test.dart';
import 'package:flutter_native_html_to_pdf/flutter_native_html_to_pdf.dart';
import 'package:flutter_native_html_to_pdf/flutter_native_html_to_pdf_platform_interface.dart';
import 'package:flutter_native_html_to_pdf/flutter_native_html_to_pdf_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockFlutterNativeHtmlToPdfPlatform
    with MockPlatformInterfaceMixin
    implements FlutterNativeHtmlToPdfPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final FlutterNativeHtmlToPdfPlatform initialPlatform = FlutterNativeHtmlToPdfPlatform.instance;

  test('$MethodChannelFlutterNativeHtmlToPdf is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelFlutterNativeHtmlToPdf>());
  });

  test('getPlatformVersion', () async {
    FlutterNativeHtmlToPdf flutterNativeHtmlToPdfPlugin = FlutterNativeHtmlToPdf();
    MockFlutterNativeHtmlToPdfPlatform fakePlatform = MockFlutterNativeHtmlToPdfPlatform();
    FlutterNativeHtmlToPdfPlatform.instance = fakePlatform;

    expect(await flutterNativeHtmlToPdfPlugin.getPlatformVersion(), '42');
  });
}
