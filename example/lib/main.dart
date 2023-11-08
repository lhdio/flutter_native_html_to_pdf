import 'dart:io';

import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter_native_html_to_pdf/flutter_native_html_to_pdf.dart';
import 'package:path_provider/path_provider.dart';
import 'package:share_plus/share_plus.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String? generatedPdfFilePath;

  final _flutterNativeHtmlToPdfPlugin = FlutterNativeHtmlToPdf();

  @override
  void initState() {
    super.initState();
    generateExampleDocument();
  }

  Future<void> generateExampleDocument() async {
    const htmlContent = """
   <!DOCTYPE html>
<html>
<head>
    <title>Sample HTML Page</title>
</head>
<body>
    <h1>Welcome to My Website!</h1>
    <p>This is a sample paragraph text.</p>
    <img src="https://picsum.photos/200/300" alt="Description of the image">
</body>
</html>
    """;

    Directory appDocDir = await getApplicationDocumentsDirectory();
    final targetPath = appDocDir.path;
    const targetFileName = "mytext";
    final generatedPdfFile =
        await _flutterNativeHtmlToPdfPlugin.convertHtmlToPdf(
      html: htmlContent,
      targetDirectory: targetPath,
      targetName: targetFileName,
    );

    generatedPdfFilePath = generatedPdfFile?.path;
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        home: Scaffold(
      appBar: AppBar(
        title: const Text("Flutter Native Html to PDF"),
      ),
      body: Center(
        child: ElevatedButton(
          child: const Text("Share PDF"),
          onPressed: () async {
            await Share.shareXFiles(
              [XFile(generatedPdfFilePath!)],
              text: 'This is pdf file',
            );
          },
        ),
      ),
    ));
  }
}
