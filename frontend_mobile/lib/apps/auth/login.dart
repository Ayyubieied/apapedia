// ignore_for_file: use_build_context_synchronously

import 'package:flutter/material.dart';
import 'package:frontend_mobile/apps/auth/register.dart';
import 'package:provider/provider.dart';
import 'package:frontend_mobile/components/drawer.dart';
// import 'package:jejakarbon_flutter/apps/homepage/homePage.dart';

class LoginPage extends StatefulWidget {
  const LoginPage({super.key});

  @override
  State<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  final _loginFormKey = GlobalKey<FormState>();
  bool isPasswordVisible = false;
  void togglePasswordView() {
    setState(() {
      isPasswordVisible = !isPasswordVisible;
    });
  }

  String username = "";
  String password1 = "";

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Login"),
        flexibleSpace: Container(
          decoration: const BoxDecoration(
            gradient: LinearGradient(
                colors: [
                  Color.fromARGB(255, 96, 183, 88),
                  Color.fromARGB(255, 130, 230, 126),
                ],
                begin: FractionalOffset(0.0, 0.0),
                end: FractionalOffset(1.0, 0.0),
                stops: [0.0, 1.0],
                tileMode: TileMode.clamp),
          ),
        ),
      ),
      body: Column(
        children: [
          SingleChildScrollView(
            child: Container(
              padding: const EdgeInsets.all(10),
              child: Column(
                children: <Widget>[
                  Form(
                    key: _loginFormKey,
                    child: Padding(padding: const EdgeInsets.all(30),
                      child: Column(
                        children: [
                          Container(
                            padding: const EdgeInsets.all(5),
                            decoration: BoxDecoration(
                              color: Colors.white,
                              borderRadius: BorderRadius.circular(10),
                              boxShadow: const [BoxShadow(
                                color: Color.fromARGB(87, 120, 219, 141),
                                blurRadius: 5,
                                offset: Offset(3,3)
                              )]
                            ),
                            child: Column(
                              children: [
                                Container(
                                  padding: const EdgeInsets.all(8),
                                  decoration: const BoxDecoration(
                                      border: Border(bottom: BorderSide(color: Color.fromARGB(107, 158, 158, 158)))
                                      ),
                                  child: TextFormField(
                                      decoration: InputDecoration(
                                        hintText: 'Insert username',
                                        labelText: 'Username',
                                        border: InputBorder.none,
                                      ),
                                      onChanged: (String value) {
                                        setState(() {
                                          username = value;
                                        });
                                      },
                                      onSaved: (String? value) {
                                        username = value!;
                                      },
                                      autovalidateMode:
                                          AutovalidateMode.onUserInteraction,
                                      validator: (value) {
                                        if (value == null || value.isEmpty) {
                                          return "Username tidak boleh kosong";
                                        }
                                        return null;
                                      }),
                                ),
                                Container(
                                  padding: const EdgeInsets.all(8),
                                  decoration: const BoxDecoration(
                                      ),
                                  child: TextFormField(
                                    obscureText: true,
                                      decoration: InputDecoration(
                                        hintText: 'Type password',
                                        labelText: 'Password',
                                        border: InputBorder.none,
                                      ),
                                     onChanged: (String? value) {
                                        setState(() {
                                          password1 = value!;
                                        });
                                      },
                                      onSaved: (String? value) {
                                        setState(() {
                                          password1 = value!;
                                        });
                                      },
                                      autovalidateMode:
                                          AutovalidateMode.onUserInteraction,
                                      validator: (value) {
                                        if (value == null || value.isEmpty) {
                                          return "Password tidak boleh kosong";
                                        }
                                        return null;
                                      },)
                                  )
                                ],
                                ),
                              ),
                              SizedBox(height: 40,),
                              Container(
                            height: 40,
                              width: 150,
                              decoration: BoxDecoration(
                                gradient: const LinearGradient(
                                  colors: [
                                    Color.fromARGB(255, 55, 157, 46),
                                    Color.fromARGB(255, 130, 232, 126),
                                  ], 
                                ),
                                borderRadius: const BorderRadius.all(Radius.circular(25)),
                                boxShadow: [BoxShadow(
                                  color: Colors.green.withOpacity(0.2),
                                  spreadRadius: 4,
                                  blurRadius: 10,
                                  offset: const Offset(0,3),
                                )]
                              ),
                            child: Center(
                              child: GestureDetector(
                                onTap: () async {
                                  
                                },
                                child: const Text(
                                  'Login',
                                  style: TextStyle(
                                      color: Colors.white,
                                      fontWeight: FontWeight.bold,
                                      fontSize: 14),
                                ),
                              ),
                            ),
                          ),
                        ]
                      ),
      
                    ),
                  ),
                ]
              ),
            ),
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              const Text(
                'Belum memiliki akun?',
                style: TextStyle(fontSize: 14, color: Colors.black),
              ),
              GestureDetector(
                onTap: () {
                  Navigator.pushReplacement(
                    context,
                    MaterialPageRoute(
                        builder: (context) => const RegisterPage()),
                  );
                },
                child: const Text(
                  ' Register',
                  style: TextStyle(
                      fontSize: 14,
                      color: Colors.green,
                      fontWeight: FontWeight.bold),
                ),
              ),
            ],
          ),
        ],
      ),
    );
  }
}
