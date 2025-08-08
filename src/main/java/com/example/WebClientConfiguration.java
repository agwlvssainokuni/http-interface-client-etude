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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

/**
 * WebClientとHTTP Service Proxy Factoryの設定クラス。
 *
 * <p>HTTP Interface機能で使用するWebClientとHttpServiceProxyFactoryのBean定義を行います。</p>
 */
@Configuration
public class WebClientConfiguration {

    /**
     * WebClientのBean定義。
     *
     * @param baseUri バックエンドサービスのベースURI
     * @return 設定済みのWebClientインスタンス
     */
    @Bean
    public WebClient webClient(@Value("${backend.uri}") String baseUri) {
        return WebClient.builder().baseUrl(baseUri).build();
    }

    /**
     * HttpServiceProxyFactoryのBean定義。
     *
     * @param webClient HTTP通信に使用するWebClient
     * @return 設定済みのHttpServiceProxyFactoryインスタンス
     */
    @Bean
    public HttpServiceProxyFactory httpServiceProxyFactory(WebClient webClient) {
        WebClientAdapter adapter = WebClientAdapter.create(webClient);
        return HttpServiceProxyFactory.builderFor(adapter).build();
    }

}
