Super Duo
=============

Added enhancements to two apps in order to make them production ready. The work included ensuring errors were handled gracefully, building a widget for the home screen, support for screen readers, optimizations for localization, and barcode scanning via a library.

### **_Introduction:_**

This project contains two apps: **Alexandria** and **Football Scores**.

* **Alexandria**: A book list and barcode scanner app.

* **Football Scores**: An app that tracks current and future football (soccer) matches.

Need to improve two apps to improve User Experience (UX) and accessibility, implement features such as, scanner and widgets.


### **_Completed user stories:_**

#### Required:

_Alexandria:_

* Alexandria has barcode scanning functionality.
* The two errors mentioned in the UX review are handled.

_Football Scores:_

* Football Scores can be displayed in a widget.
* Football Scores app has content descriptions for all buttons, images, and text.
* Football Scores app supports layout mirroring.

#### Extra:

_Alexandria:_

* Alexandria’s barcode scanning functionality does not require the installation of a separate app on first use.
* Extra error cases are found, accounted for, and called out in code comments.

_Football Scores:_

* Football Scores also support a collection widget.
* Strings are all included in the strings.xml file and untranslatable strings have a translatable tag marked to false.

#### Libraries Used:

_Alexandria:_
* ZXing: https://github.com/zxing/zxing
* ZXing Android Embedded: https://github.com/journeyapps/zxing-android-embedded
