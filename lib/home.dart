import 'dart:async';
import 'dart:convert';
import 'dart:ffi';
import 'dart:typed_data';

// import 'package:fixnum/fixnum.dart';
import 'package:flutter/material.dart';

// import 'package:android_intent/android_intent.dart';
import 'package:flutter/services.dart';

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key});

  @override
  _MyHomePageState createState() => _MyHomePageState();
  static ValueNotifier callback = ValueNotifier(false);
}

class _MyHomePageState extends State<MyHomePage> {
  _MyHomePageState() {
    _MyHomePageState.instance.configureChannel();
  }

  static final _MyHomePageState instance = _MyHomePageState._init();

  _MyHomePageState._init();

  static String GloubalResult = "";

  @override
  void initState() {
    super.initState();
    MyHomePage.callback.addListener(() {
      setState(() {
        resulController.text = GloubalResult;
      });
    });
  }

  // static const methodChannel = MethodChannel('com.mada.intersoft/method');
  MethodChannel methodChannel =
      const MethodChannel('com.mada.intersoft/method');
  static const channelName =
      'com.mada.intersoft/method'; // this channel name needs to match the one in Native method channel
  TextEditingController amountController = TextEditingController();
  TextEditingController resulController = TextEditingController();

  Future<void> _trigerSale(String amount) async {
    try {
      String response = "";

      final String result = await methodChannel
          .invokeMethod('Sale', <String, dynamic>{"Amount": amount});
    } on PlatformException catch (e) {
      print(e);
    }
  }

  Future<void> _trigerPrint(dynamic img) async {
    try {
      final String result = await methodChannel
          .invokeMethod('Print', <String, dynamic>{"img": img});
    } on PlatformException catch (e) {
      print(e);
    }
  }

  void configureChannel() {
    methodChannel = const MethodChannel(channelName);
    methodChannel.setMethodCallHandler(methodHandler); // set method handler
  }

  Future<void> methodHandler(MethodCall call) async {
    final String idea = call.arguments;

    switch (call.method) {
      case "showResult":
        {
          try {
            GloubalResult = idea;
            MyHomePage.callback.value = 1;
          } catch (error) {
            print('never reached ---- - - - - -$error');
          }
        }

        break;
      default:
        print('no method handler for method ${call.method}');
    }
  }

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        body: Padding(
          padding: const EdgeInsets.all(12),
          child: Center(
            child: SingleChildScrollView(
              physics: const ScrollPhysics(),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  TextField(
                    controller: amountController,
                    keyboardType: TextInputType.number,
                    decoration: const InputDecoration(
                      border: OutlineInputBorder(),
                      hintText: 'Enter Amount',
                    ),
                  ),
                  const SizedBox(
                    height: 10,
                  ),
/*              Center(
                      child: Image(image: AssetImage("assets/sample_receipt.png"),)),*/

                  ElevatedButton(
                      onPressed: () => {
                            setState(() {
                              GloubalResult = "";
                              resulController.text = "";
                              MyHomePage.callback.value = 0;
                            }),
                            _trigerSale(amountController.text)
                          },
                      child: const Text('pay')),
                  const SizedBox(
                    height: 10,
                  ),
                  ElevatedButton(
                      onPressed: () async => {
                            setState(() {
                              GloubalResult = "";
                              resulController.text = "";
                              MyHomePage.callback.value = 0;
                            }),
                            _trigerPrint(await _convertImagetoByte(
                                const AssetImage("assets/sample_receipt.png")))
                          },
                      child: const Text('print')),
                  const SizedBox(
                    height: 10.0,
                  ),
                  Container(
                    margin: const EdgeInsets.all(12),
                    height: 15 * 24.0,
                    child: TextField(
                      controller: resulController,
                      maxLines: 15,
                      decoration: InputDecoration(
                        hintText: "result",
                        fillColor: Colors.grey[300],
                        filled: true,
                      ),
                    ),
                  ),
                ],
              ),
            ),
          ),
        ),
      ),
    );
  }

  _convertImagetoByte(AssetImage) async {
    final byteData = await rootBundle.load('assets/sample_receipt.png');
    Uint8List bytelist = byteData.buffer.asUint8List();
    return bytelist;
  }
}
