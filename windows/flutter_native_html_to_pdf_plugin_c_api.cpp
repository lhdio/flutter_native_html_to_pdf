#include "include/flutter_native_html_to_pdf/flutter_native_html_to_pdf_plugin_c_api.h"

#include <flutter/plugin_registrar_windows.h>

#include "flutter_native_html_to_pdf_plugin.h"

void FlutterNativeHtmlToPdfPluginCApiRegisterWithRegistrar(
    FlutterDesktopPluginRegistrarRef registrar) {
  flutter_native_html_to_pdf::FlutterNativeHtmlToPdfPlugin::RegisterWithRegistrar(
      flutter::PluginRegistrarManager::GetInstance()
          ->GetRegistrar<flutter::PluginRegistrarWindows>(registrar));
}
