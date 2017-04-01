package io.github.yutoeguma.exeptions;

import lombok.NoArgsConstructor;

/**
 *
 * リクエストの形式が正しくない際に返す例外です
 * これを投げると 400 - Bad Request がページになるようにハンドリングします
 *
 * @author yuto.eguma
 */
@NoArgsConstructor
public class IncorrectRequestException extends RuntimeException {

    public IncorrectRequestException(String msg) {
        super(msg);
    }
}
