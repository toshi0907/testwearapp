# testwearapp

Pixel Watch 2(Wear OS)向けのスタンドアロンアプリ作成テスト。Kotlin + Compose for Wear OS + Gradle(Kotlin DSL)。

## 開発体制

- スマホ + Claude Code on the web のみで開発する(`android-mobile-only-dev` スキル準拠)
- サンドボックスでは `dl.google.com` に接続できず `./gradlew` は完走しない。コードを読んで検証し、ビルド確認は GitHub Actions(`.github/workflows/build.yml`)に委ねる
- CIは全push/PRで `assembleDebug` を実行し APK を Artifact に上げる。main への push 時のみ Release `latest` を更新する
- ビルド番号は `-PbuildNumber=${GITHUB_RUN_NUMBER}` で `versionCode` / `BuildConfig.BUILD_NUMBER` に埋め込む
- デバッグ署名は `app/debug.keystore`(公開前提のデバッグ専用鍵)で固定。差し替えないこと
- バージョンは `gradle/libs.versions.toml` で一元管理。Kotlin を上げる際は `kotlin` と Compose Compiler プラグインが同じ `version.ref` を使っていることを保つ

## 利用可能なスキル(myskills 由来)

- `.claude/skills/` は実ディレクトリで、submodule `.myskills/claude-skills/` 配下の各スキルへの symlink がスキルごとに登録されている。ローカルスキルは通常ディレクトリとして共存可
- セッション開始時に `.claude/hooks/myskills-skills-sync.sh` が `.myskills` を最新化し、symlink の追加・リンク切れ削除を行う(既存は上書きしない)
- 実装作業前に `.claude/skills/` の各 `SKILL.md` の description を確認し、該当するものを優先的に使う
- フックが「myskills同期による未コミットの差分」を報告したら、該当パスだけを専用コミット(例: `chore: sync myskills`)にする。`git add -A` / `git commit -a` で作業コミットに巻き込まない
