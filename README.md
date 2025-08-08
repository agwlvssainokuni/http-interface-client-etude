# HTTP Interface Client Etude

Spring Boot 3.5 のHTTP Interface機能を学習するためのサンプルプロジェクトです。

## 概要

このプロジェクトは、Spring WebFluxのHTTP Interfaceを使用してHTTP通信を宣言的に行う方法を学習することを目的としています。同期・非同期の通信パターン、GET・POSTリクエスト、Form URL-encoded・JSON形式でのデータ送信など、様々なHTTP通信パターンを実装しています。

## 主な機能

- **HTTP Interface**: Spring Boot 3の宣言的HTTP通信機能の活用
- **同期・非同期通信**: `ResponseEntity` と `Mono<ResponseEntity>` の両パターンに対応
- **複数のHTTPメソッド**: GET、POST（Form、JSON）リクエストの実装
- **設定の分離**: WebClient設定とAPI設定の責務を分けた構成

## 技術スタック

- **Java**: 21
- **Spring Boot**: 3.5.4
- **Spring WebFlux**: リアクティブHTTPクライアント
- **Gradle**: 8.14.3
- **Docker Compose**: 外部APIサーバ（httpbin）の起動

## セットアップ

### 前提条件

- Java 21以上
- Docker & Docker Compose

### 実行手順

1. **リポジトリのクローン**
   ```bash
   git clone <repository-url>
   cd http-interface-client-etude
   ```

2. **外部APIサーバの起動**
   ```bash
   docker-compose up -d
   ```
   
   これにより、httpbinサーバが `http://localhost:8999` で起動します。

3. **アプリケーションの実行**
   ```bash
   ./gradlew bootRun
   ```

4. **ビルド**
   ```bash
   ./gradlew build
   ```

## プロジェクト構成

```
src/main/java/com/example/
├── Main.java                    # アプリケーションのエントリーポイント
├── Runner.java                  # HTTP通信のテストを行うランナー
├── WebClientConfiguration.java # WebClientとHttpServiceProxyFactoryの設定
└── api/
    ├── ApiConfiguration.java   # APIサービスのBean定義
    ├── AnythingService.java    # HTTP Interfaceサービス定義
    └── dto/
        ├── AnythingRequestDto.java  # リクエストDTO
        └── AnythingResponseDto.java # レスポンスDTO
```

## HTTP Interface実装例

### サービスインターフェース

```java
@HttpExchange("/anything")
public interface AnythingService {
    
    // 同期GETリクエスト
    @GetExchange
    ResponseEntity<AnythingResponseDto> get(
        @RequestParam("param1") String param1,
        @RequestParam("param2") String param2
    );
    
    // 非同期POSTリクエスト（JSON）
    @PostExchange
    Mono<ResponseEntity<AnythingResponseDto>> asyncPostJson(
        @RequestBody AnythingRequestDto request
    );
}
```

### 設定クラス

WebClient と HttpServiceProxyFactory の設定：

```java
@Configuration
public class WebClientConfiguration {
    
    @Bean
    public WebClient webClient(@Value("${backend.uri}") String baseUri) {
        return WebClient.builder().baseUrl(baseUri).build();
    }
    
    @Bean
    public HttpServiceProxyFactory httpServiceProxyFactory(WebClient webClient) {
        WebClientAdapter adapter = WebClientAdapter.create(webClient);
        return HttpServiceProxyFactory.builderFor(adapter).build();
    }
}
```

## 実行されるHTTP通信パターン

アプリケーション実行時に以下のHTTP通信が順次実行されます：

1. **同期GET**: クエリパラメータ付きGETリクエスト
2. **同期POST（Form）**: Form URL-encodedでのPOSTリクエスト
3. **同期POST（JSON）**: JSONボディでのPOSTリクエスト
4. **非同期GET**: リアクティブなGETリクエスト
5. **非同期POST（Form）**: リアクティブなFormPOSTリクエスト
6. **非同期POST（JSON）**: リアクティブなJSONPOSTリクエスト

## 設定

`src/main/resources/application.properties` で設定を変更できます：

```properties
backend.protocol=http
backend.host=localhost
backend.port=8999
backend.path=
backend.uri=${backend.protocol}://${backend.host}:${backend.port}${backend.path}
```

## ログ設定

詳細なHTTP通信ログを確認できるよう、以下のログレベルが設定されています：

- `org.springframework.web.reactive=TRACE`: WebFluxの詳細ログ
- `com.example=TRACE`: アプリケーションログ

## 学習ポイント

- **宣言的HTTP通信**: アノテーションベースでのHTTP通信実装
- **リアクティブプログラミング**: MonoとFluxを使った非同期処理
- **設定の分離**: 責務に応じたConfiguration分割
- **Spring Boot 3**: 最新のSpring Boot機能の活用

## ライセンス

Apache License 2.0
