# testwearapp

Pixel Watch 2(Wear OS 4 / API 33〜)にインストール可能なスタンドアロン Wear OS アプリの作成テスト。

- 言語・UI: Kotlin + Compose for Wear OS
- minSdk 30 / targetSdk 34
- ビルド: GitHub Actions(ローカルの Android SDK は不要)

## APK の入手

- 作業ブランチ / PR: Actions の実行結果の Artifact `testwearapp-debug-<ビルド番号>`
- main: Release [`latest`](../../releases/tag/latest) の `testwearapp-debug.apk`

## Pixel Watch 2 へのインストール

1. ウォッチで「設定 > システム > 端末情報 > バージョン」のビルド番号を7回タップして開発者向けオプションを有効化
2. 「開発者向けオプション」で「ADBデバッグ」と「ワイヤレスデバッグ」を有効化
3. ウォッチとスマホを同じ Wi-Fi に接続し、ADB クライアント(PC の `adb`、またはスマホの ADB 対応アプリ)からペア設定して接続
4. `adb install -r testwearapp-debug.apk`(署名鍵を固定しているため `-r` で上書き更新できる)

※ ビルド番号(`versionCode`)は PR と main で共通の連番のため、PR のビルドを入れた後に番号の小さい main のビルドへ戻す場合は `adb install -r -d`(ダウングレード許可)を使う。

## Claude Code 用の設定

myskills を submodule(`.myskills`)として取り込み、`.claude/skills/` にスキルごとの symlink を登録している。
詳細は [myskills の組み込み手順](https://github.com/toshi0907/myskills/blob/main/docs/skill-integration-submodule.md) を参照。
