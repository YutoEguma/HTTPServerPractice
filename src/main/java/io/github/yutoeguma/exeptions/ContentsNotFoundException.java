package io.github.yutoeguma.exeptions;

import lombok.NoArgsConstructor;

/**
 * 指定されたコンテンツが存在しない場合に投げられる例外です
 * これを投げると404がページになるようにハンドリングします
 *
 * @author yuto.eguma
 */
@NoArgsConstructor
public class ContentsNotFoundException extends RuntimeException {

    public ContentsNotFoundException(String msg) {
        super(msg);
    }
}
