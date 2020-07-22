package com.muskteer.tm.ai;

public enum UnknowSentence {

    one("我还小，不太明白"),
    two("俺不懂"),
    three("宝贝，能不能说一些电影相关的呀"),
        four("你是哪个村的猪？"),
            five("你说的都对"),
    six("俺是影评达人，你呢"),
        seven("你以为我是维基百科？"),
            eight("哟"),
                nine("额，我只知道我爱你"),
                    ten("多说话 少读书");
    String name;
    UnknowSentence(String name){this.name = name;}

}
