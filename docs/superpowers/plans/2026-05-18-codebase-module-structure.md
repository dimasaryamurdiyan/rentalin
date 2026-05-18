# Codebase Module Structure Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Initialize the RentalIn Android codebase with the MVP package and Gradle module structure described in `AGENTS.md`, without implementing rental business logic.

**Architecture:** Use a lightweight Now in Android-style module skeleton. `app` owns startup only, `core:*` modules define shared boundaries, and `feature:*` modules own future screen surfaces without depending on each other.

**Tech Stack:** Kotlin, Android Gradle Plugin, Jetpack Compose, Material 3, Gradle version catalogs, included `build-logic` convention plugins.

---

## Acceptance Criteria

- `settings.gradle.kts` includes `build-logic` and the MVP modules from `AGENTS.md`.
- `core:model`, `core:domain`, and `core:testing` are pure Kotlin/JVM modules where Android APIs are not needed.
- Android/Compose modules compile through shared convention plugins.
- `core:designsystem` owns `RentalInTheme`, typography, and the Shop Counter color palette.
- `app` depends on `core:designsystem` and does not contain feature business logic.
- Feature modules do not depend on each other directly.
- `core:data` may depend on `core:database`, `core:datastore`, and `core:model`.
- `core:domain` may depend on `core:data` and `core:model`.
- `core:ui` may depend on `core:model` and `core:designsystem`.
- No optional later modules are added yet: `app-rentalin-catalog`, `core:notifications`, `core:export`, or `feature:reports`.
- No login, cloud sync, online payments, notifications, marketplace behavior, database schema, repositories, or rental business rules are implemented.
- `./gradlew :app:compileDebugKotlin` passes.

## Module/File Structure

- Modify `settings.gradle.kts` to include `build-logic`, `core:*`, and `feature:*` modules.
- Modify `gradle/libs.versions.toml` to add Android library, Kotlin Android, and Kotlin JVM plugin aliases.
- Create `build-logic/build.gradle.kts` and convention plugin Kotlin files under `build-logic/src/main/kotlin/convention`.
- Modify `app/build.gradle.kts` to use the application Compose convention and depend on `core:designsystem`.
- Create `build.gradle.kts` files for each core and feature module.
- Move theme code from `app/src/main/java/com/rentalin/app/ui/theme` into `core/designsystem/src/main/kotlin/com/rentalin/core/designsystem/theme`.
- Create small package anchors for `core:*` and `feature:*` modules.
- Update `MainActivity.kt` to import `RentalInTheme` from `core:designsystem`.

## Tasks

### Task 1: Register Build Plugins and Modules

**Files:**
- Modify: `settings.gradle.kts`
- Modify: `gradle/libs.versions.toml`
- Create: `build-logic/build.gradle.kts`
- Create: `build-logic/src/main/kotlin/convention/AndroidApplicationConventionPlugin.kt`
- Create: `build-logic/src/main/kotlin/convention/AndroidLibraryConventionPlugin.kt`
- Create: `build-logic/src/main/kotlin/convention/AndroidLibraryComposeConventionPlugin.kt`
- Create: `build-logic/src/main/kotlin/convention/KotlinJvmConventionPlugin.kt`

- [ ] **Step 1: Add plugin aliases**

Add aliases for Android library, Kotlin Android, and Kotlin JVM plugins so app/core/feature modules can share convention plugins.

- [ ] **Step 2: Add build-logic included build**

Add `includeBuild("build-logic")` under `pluginManagement`.

- [ ] **Step 3: Include MVP modules**

Register all required `core:*` and `feature:*` modules from `AGENTS.md`.

- [ ] **Step 4: Add convention plugins**

Create one convention plugin per repeated Gradle configuration surface.

### Task 2: Create Module Build Files

**Files:**
- Modify: `app/build.gradle.kts`
- Create: `core/common/build.gradle.kts`
- Create: `core/model/build.gradle.kts`
- Create: `core/database/build.gradle.kts`
- Create: `core/datastore/build.gradle.kts`
- Create: `core/data/build.gradle.kts`
- Create: `core/domain/build.gradle.kts`
- Create: `core/designsystem/build.gradle.kts`
- Create: `core/ui/build.gradle.kts`
- Create: `core/testing/build.gradle.kts`
- Create: `feature/dashboard/build.gradle.kts`
- Create: `feature/rentals/build.gradle.kts`
- Create: `feature/inventory/build.gradle.kts`
- Create: `feature/customers/build.gradle.kts`
- Create: `feature/settings/build.gradle.kts`

- [ ] **Step 1: Convert app to convention plugin**

Use `rentalin.android.application` and `rentalin.android.application.compose`.

- [ ] **Step 2: Add core dependencies**

Wire only the allowed module dependencies from `AGENTS.md`.

- [ ] **Step 3: Add feature dependencies**

Wire features to allowed core modules only.

### Task 3: Move Design System Ownership

**Files:**
- Create: `core/designsystem/src/main/kotlin/com/rentalin/core/designsystem/theme/Color.kt`
- Create: `core/designsystem/src/main/kotlin/com/rentalin/core/designsystem/theme/Theme.kt`
- Create: `core/designsystem/src/main/kotlin/com/rentalin/core/designsystem/theme/Type.kt`
- Delete: `app/src/main/java/com/rentalin/app/ui/theme/Color.kt`
- Delete: `app/src/main/java/com/rentalin/app/ui/theme/Theme.kt`
- Delete: `app/src/main/java/com/rentalin/app/ui/theme/Type.kt`
- Modify: `app/src/main/java/com/rentalin/app/MainActivity.kt`

- [ ] **Step 1: Add RentalIn palette**

Define the Shop Counter palette from `AGENTS.md`.

- [ ] **Step 2: Add `RentalInTheme`**

Disable dynamic color by default so the app uses product colors consistently.

- [ ] **Step 3: Point app at `core:designsystem`**

Import `com.rentalin.core.designsystem.theme.RentalInTheme`.

### Task 4: Add Package Anchors

**Files:**
- Create one `PackageAnchor.kt` or focused placeholder per new module package.

- [ ] **Step 1: Add anchors**

Each module gets one tiny public marker object to make the package compile and document responsibility.

- [ ] **Step 2: Avoid fake behavior**

Do not add empty repositories, fake DAOs, rental rules, or screen ViewModels.

### Task 5: Verify

**Files:**
- No source edits expected.

- [ ] **Step 1: Compile app Kotlin**

Run: `./gradlew :app:compileDebugKotlin`

Expected: `BUILD SUCCESSFUL`.

- [ ] **Step 2: Inspect git diff**

Run: `git status --short`

Expected: only planned module, Gradle, theme, and plan files changed.

## Commit Plan

Keep commits small and ordered by layer so each commit is reviewable on its own.

### Commit 1: Plan

**Scope:** Document the accepted scaffold plan and acceptance criteria.

```bash
git add .gitignore docs/superpowers/plans/2026-05-18-codebase-module-structure.md
git commit -m "docs: add module scaffold plan"
```

### Commit 2: Build Conventions

**Scope:** Add plugin aliases and `build-logic` convention plugins for app, Android library, Compose, and Kotlin JVM modules.

```bash
git add build.gradle.kts gradle/libs.versions.toml build-logic
git commit -m "build: add module convention plugins"
```

### Commit 3: Module Registration

**Scope:** Register the MVP `core:*` and `feature:*` modules and add their Gradle build files with allowed dependency boundaries.

```bash
git add settings.gradle.kts app/build.gradle.kts core/*/build.gradle.kts feature/*/build.gradle.kts
git commit -m "build: register rentalin modules"
```

### Commit 4: Design System Ownership

**Scope:** Move the app theme into `core:designsystem`, apply the Shop Counter palette, and point `MainActivity` at the shared theme.

```bash
git add app/src/main/java/com/rentalin/app/MainActivity.kt
git add app/src/main/java/com/rentalin/app/ui/theme
git add core/designsystem/src/main/kotlin/com/rentalin/core/designsystem/theme
git commit -m "refactor: move theme to design system"
```

### Commit 5: Package Anchors

**Scope:** Add minimal package anchors for the new modules without business logic, database schema, repositories, or screen ViewModels.

```bash
git add core/*/src/main/kotlin feature/*/src/main/kotlin
git commit -m "chore: add module package anchors"
```

### Final Verification Before Commit Series

Run this before the first commit, and again after the final commit if any staged set changes during review:

```bash
./gradlew :app:compileDebugKotlin :core:common:compileKotlin :core:model:compileKotlin :core:domain:compileKotlin :core:testing:compileKotlin :core:database:compileDebugKotlin :core:datastore:compileDebugKotlin :core:data:compileDebugKotlin :core:designsystem:compileDebugKotlin :core:ui:compileDebugKotlin :feature:customers:compileDebugKotlin :feature:dashboard:compileDebugKotlin :feature:inventory:compileDebugKotlin :feature:rentals:compileDebugKotlin :feature:settings:compileDebugKotlin
```

Expected: `BUILD SUCCESSFUL`.
