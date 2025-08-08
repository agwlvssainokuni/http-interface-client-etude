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

package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * HTTP Interface Clientの練習用メインクラス。
 *
 * <p>Spring Bootアプリケーションとして起動し、HTTP Interface機能を使用して
 * 外部APIとの通信を行うサンプルコードを実行します。</p>
 */
@SpringBootApplication
public class Main {

    /**
     * アプリケーションのエントリーポイント。
     *
     * @param args コマンドライン引数
     */
    public static void main(String[] args) {
        System.exit(doMain(args));
    }

    /**
     * Spring Bootアプリケーションを起動し、実行後に終了コードを返します。
     *
     * @param args コマンドライン引数
     * @return アプリケーションの終了コード
     */
    public static int doMain(String[] args) {
        try (var app = SpringApplication.run(Main.class, args)) {
            return SpringApplication.exit(app);
        }
    }
}
