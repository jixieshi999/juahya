juahya
======

juahya is android real time xml layout inflater

version 1.0 20140522


what can juahya do ?
======

you can use juahya to prase your custom layout xml file from internet,sdcard,sqlite ...

how to build your custom view inflater
    1.add IFXXX on com.xml.inflate.inflater
    xxx is android view
    2.config you IFxxx to com.xml.inflate.factory.IFLalterSImpleLinearLayoutFactory
    3.change your xml layout you can  see some of the things juahya used in his magic tricks ,eg file [test.xml](jixieshi999.github.io/ilife/juahya/test.xml)

how to build your custom juahyaview inflater
    1.add IFJxxx on com.xml.inflate.inflater.juahya
    IFJxxx is extends IFXXX and add juahya ATTRIBUTE
    2.config you IFxxx to com.xml.inflate.factory.IFLalterSImpleLinearLayoutFactory
    3.change your xml layout you can  see some of the things juahya used in his magic tricks

Acknowledgements
======

[Android-Universal-Image-Loader](https://github.com/nostra13/Android-Universal-Image-Loader) for the img lazy loader.

======
ps : after finish it i thought is like a html praser lol

