#ifndef FLUTTER_PLUGIN_FLUTTER_NATIVE_HTML_TO_PDF_PLUGIN_H_
#define FLUTTER_PLUGIN_FLUTTER_NATIVE_HTML_TO_PDF_PLUGIN_H_

#include <flutter/method_channel.h>
#include <flutter/plugin_registrar_windows.h>

#include <memory>

namespace flutter_native_html_to_pdf {

class FlutterNativeHtmlToPdfPlugin : public flutter::Plugin {
 public:
  static void RegisterWithRegistrar(flutter::PluginRegistrarWindows *registrar);

  FlutterNativeHtmlToPdfPlugin();

  virtual ~FlutterNativeHtmlToPdfPlugin();

  // Disallow copy and assign.
  FlutterNativeHtmlToPdfPlugin(const FlutterNativeHtmlToPdfPlugin&) = delete;
  FlutterNativeHtmlToPdfPlugin& operator=(const FlutterNativeHtmlToPdfPlugin&) = delete;

  // Called when a method is called on this plugin's channel from Dart.
  void HandleMethodCall(
      const flutter::MethodCall<flutter::EncodableValue> &method_call,
      std::unique_ptr<flutter::MethodResult<flutter::EncodableValue>> result);
};

}  // namespace flutter_native_html_to_pdf

#endif  // FLUTTER_PLUGIN_FLUTTER_NATIVE_HTML_TO_PDF_PLUGIN_H_
