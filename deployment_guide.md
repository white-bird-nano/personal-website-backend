# デプロイ手順書 (Google Cloud Artifact Registry & Cloud Run)

Jibを使用してアプリケーションをビルドし、Google Cloud Artifact Registryへイメージをプッシュ、その後Cloud Runへデプロイする手順です。

## 1. 準備

### Google Cloud CLI の認証
以下のコマンドを実行して認証を行います。

```bash
gcloud auth login
gcloud auth configure-docker <REGION>-docker.pkg.dev
```
※ `<REGION>` は `asia-northeast1` など、使用するリージョンに置き換えてください。

## 2. ビルドとプッシュ (Jib)

Jibを使用してDockerデーモンなしでイメージをビルドし、Artifact Registryへ直接プッシュします。

### 実行コマンド
プロジェクトID、リポジトリ名、イメージ名を環境に合わせて指定して実行します。

```bash
./gradlew build \
  -Dquarkus.container-image.push=true \
  -Dquarkus.container-image.registry=<REGION>-docker.pkg.dev \
  -Dquarkus.container-image.group=<PROJECT_ID>/<REPOSITORY_NAME> \
  -Dquarkus.container-image.name=<IMAGE_NAME> \
  -Dquarkus.container-image.tag=latest
```

#### パラメータの説明:
- `quarkus.container-image.push=true`: ビルド後にレジストリへプッシュします。
- `quarkus.container-image.registry`: Artifact Registry のホスト名。
- `quarkus.container-image.group`: プロジェクトIDとリポジトリ名。
- `quarkus.container-image.name`: イメージ名。

> [!TIP]
> `src/main/resources/application.properties` にこれらの値を記載しておくことで、コマンドライン引数を省略することも可能です。

## 3. Cloud Run へのデプロイ

プッシュしたイメージを使用して Cloud Run へデプロイします。

```bash
gcloud run deploy <SERVICE_NAME> \
  --image <REGION>-docker.pkg.dev/<PROJECT_ID>/<REPOSITORY_NAME>/<IMAGE_NAME>:latest \
  --platform managed \
  --region <REGION> \
  --allow-unauthenticated
```

## 4. トラブルシューティング

- **認証エラー**: `gcloud auth configure-docker` が正しく実行されているか確認してください。
- **ビルドエラー**: `./gradlew clean build` を試してください。
- **イメージが見つからない**: Artifact Registry のパスが正しいか、Google Cloud Console で確認してください。
