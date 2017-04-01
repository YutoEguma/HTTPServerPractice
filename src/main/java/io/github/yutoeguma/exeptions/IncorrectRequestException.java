package io.github.yutoeguma.exeptions;

/**
 *
 * リクエストの形式が正しくない際に返す例外です
 * これを投げると 400 - Bad Request がページになるようにハンドリングします
 *
 * @author yuto.eguma
 */
public class IncorrectRequestException extends RuntimeException {

    public IncorrectRequestException(String msg) {
        super(msg);
    }

    public IncorrectRequestException() {
        super();
    }
}
