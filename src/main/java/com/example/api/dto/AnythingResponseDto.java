/*
 * Copyright 2025 agwlvssainokuni
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.api.dto;

import java.util.Map;

/**
 * httpbin.org の /anything エンドポイントからのレスポンスDTO。
 *
 * @param args    クエリパラメータ
 * @param data    リクエストボディの文字列
 * @param files   アップロードされたファイル
 * @param form    フォームデータ
 * @param headers HTTPヘッダー
 * @param json    JSONデータ
 * @param method  HTTPメソッド
 * @param origin  リクエスト元のIPアドレス
 * @param url     リクエストURL
 */
public record AnythingResponseDto(
        Map<String, String> args,
        String data,
        Map<String, String> files,
        Map<String, String> form,
        Map<String, String> headers,
        Map<String, Object> json,
        String method,
        String origin,
        String url
) {
}
