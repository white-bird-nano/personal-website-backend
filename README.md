# personal-website-backend

個人ポートフォリオサイトのバックエンドAPIプロジェクトです。

## 概要

Quarkus / Kotlin / Gradle で構築した個人ポートフォリオサイトのバックエンドです。
MVP段階ではMockデータを返すREST APIを提供します。

## 技術スタック

| 技術 | バージョン | 用途 |
|------|-----------|------|
| [Quarkus](https://quarkus.io/) | 3.25.x | フレームワーク |
| Kotlin | - | 実装言語 |
| Gradle（Kotlin DSL） | - | ビルドツール |
| SmallRye Health | - | ヘルスチェック |

## APIエンドポイント

| メソッド | パス | 説明 |
|---------|------|------|
| GET | `/api/v1/profile` | プロフィール情報（自己紹介） |
| GET | `/q/health` | ヘルスチェック（SmallRye Health） |
| GET | `/q/health/live` | Liveness チェック |
| GET | `/q/health/ready` | Readiness チェック |

## 開発環境のセットアップ

### 前提条件
- JDK 21+
- Docker（コンテナビルド時）

### 開発サーバー起動（ホットリロード対応）

```bash
./gradlew quarkusDev
```

[http://localhost:8080](http://localhost:8080) でアクセスできます。

Dev UI: [http://localhost:8080/q/dev/](http://localhost:8080/q/dev/)

## ビルド

```bash
./gradlew build -x test
```

## デプロイ

Google Cloud Run へデプロイします。
詳細はインフラリポジトリ（`personal-website-infra`）を参照してください。

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./gradlew quarkusDev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./gradlew build
```

It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./gradlew build -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar build/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./gradlew build -Dquarkus.native.enabled=true
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./gradlew build -Dquarkus.native.enabled=true -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./build/personal-website-backend-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/gradle-tooling>.

## Related Guides

- SmallRye Health ([guide](https://quarkus.io/guides/smallrye-health)): Monitor service health
- Kotlin ([guide](https://quarkus.io/guides/kotlin)): Write your services in Kotlin
- REST Jackson ([guide](https://quarkus.io/guides/rest#json-serialisation)): Jackson serialization support for Quarkus REST. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it
