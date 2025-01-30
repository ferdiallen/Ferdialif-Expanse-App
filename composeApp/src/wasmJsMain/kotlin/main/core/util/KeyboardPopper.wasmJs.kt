package main.core.util

actual fun webKeyboardOpener() {
    js("if(\"virtualKeyboard\" in navigator) {\n" +
            "navigator.virtualKeyboard.overlaysContent = true; \n"+
            "  console.log(\"Supported!!\"); \n" +
            "  navigator.virtualKeyboard.show(); \n" +
            "}else {\n" +
            "  console.log(\"Not open\") \n" +
            "}")
}