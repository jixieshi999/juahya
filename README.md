juahya
======

juahya is android real time xml layout inflater<br />

version 1.0 20140522


what can juahya do ?
======

you can use juahya to prase your custom layout xml file from internet,sdcard,sqlite ...<br />

how to build your custom view inflater<br />
    1.add IFXXX on com.xml.inflate.inflater<br />
        xxx is android view<br />
    2.config you IFxxx to com.xml.inflate.factory.IFLalterSImpleLinearLayoutFactory<br />
    3.change your xml layout you can  see some of the things juahya used in his magic tricks ,<br />
    eg file [test.xml](jixieshi999.github.io/ilife/juahya/test.xml)<br />

how to build your custom juahyaview inflater<br />
    1.add IFJxxx on com.xml.inflate.inflater.juahya<br />
        IFJxxx is extends IFXXX and add juahya ATTRIBUTE<br />
    2.config you IFxxx to com.xml.inflate.factory.IFLalterSImpleLinearLayoutFactory<br />
    3.change your xml layout you can  see some of the things juahya used in his magic tricks<br />

Acknowledgements
======

[Android-Universal-Image-Loader](https://github.com/nostra13/Android-Universal-Image-Loader) for the img lazy loader<br />

======
ps : after finish it i thought it is like a html praser lol<br />

