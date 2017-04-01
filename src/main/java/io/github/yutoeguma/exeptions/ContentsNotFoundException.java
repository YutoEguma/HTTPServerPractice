package io.github.yutoeguma.exeptions;

/**
 * 指定されたコンテンツが存在しない場合に投げられる例外です
 * これを投げると 404 - not found がページになるようにハンドリングします
 *
 * @author yuto.eguma
 */
public class ContentsNotFoundException extends RuntimeException {

    public ContentsNotFoundException(String msg) {
        super(msg);
    }

    public ContentsNotFoundException() {super();}
}
