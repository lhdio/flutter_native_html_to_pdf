#ifndef FLUTTER_PLUGIN_FLUTTER_NATIVE_HTML_TO_PDF_PLUGIN_H_
#define FLUTTER_PLUGIN_FLUTTER_NATIVE_HTML_TO_PDF_PLUGIN_H_

#include <flutter_linux/flutter_linux.h>

G_BEGIN_DECLS

#ifdef FLUTTER_PLUGIN_IMPL
#define FLUTTER_PLUGIN_EXPORT __attribute__((visibility("default")))
#else
#define FLUTTER_PLUGIN_EXPORT
#endif

typedef struct _FlutterNativeHtmlToPdfPlugin FlutterNativeHtmlToPdfPlugin;
typedef struct {
  GObjectClass parent_class;
} FlutterNativeHtmlToPdfPluginClass;

FLUTTER_PLUGIN_EXPORT GType flutter_native_html_to_pdf_plugin_get_type();

FLUTTER_PLUGIN_EXPORT void flutter_native_html_to_pdf_plugin_register_with_registrar(
    FlPluginRegistrar* registrar);

G_END_DECLS

#endif  // FLUTTER_PLUGIN_FLUTTER_NATIVE_HTML_TO_PDF_PLUGIN_H_
