# Android Template Compose

[![Build Status](https://github.com/chandroidx/android_template_compose/actions/workflows/build.yml/badge.svg?branch=develop)](https://github.com/chandroidx/android_template_compose/actions)

## 📚 목차

- [✨ Overview](#overview)
- [🛠️ Requirements](#requirements)
- [🏗️ Project Structure](#project-structure)

<a id="overview"></a>

## Overview

Kotlin, Jetpack Compose, MVI architecture, multi-module structure를 기반으로 구성

Feature, data source, navigation 책임을 모듈 단위로 분리하여 화면 추가와 기능 확장을 일관된 구조 안에서 진행할 수 있도록 설계

주요 방향.

- Compose 기반 UI 개발
- MVI 패턴 기반 상태 관리
- Feature 단위 presentation 모듈 분리
- `api` / `impl` 모듈 구분을 통한 의존성 경계 관리
- Gradle Version Catalog와 Convention Plugin 기반 빌드 설정 관리

<a id="requirements"></a>

## Requirements

| 항목                     | 기준      |
|------------------------|---------|
| Android Studio         | Quail 2 2026.1.2   |
| JDK                    | 17      |
| Gradle Wrapper         | 9.6.1   |
| Kotlin                 | 2.4.10  |
| minSdk                 | 24      |
| targetSdk / compileSdk | 37 / 37 |

<a id="project-structure"></a>

## Project Structure

<!-- TREE_START -->
![Navigation graph](.navgraph/nav-graph.png)
<!-- TREE_END -->

### 모듈 책임

**App**

- `app`: application entry, flavor/build type, 최종 구현 모듈 조립
- `buildconfig`: application build configuration 제공
- `buildconfig-stub`: local 또는 stub configuration 제공

**Domain & Data**

- `domain`: entity, repository interface, use case
- `data`: domain contract에 대한 repository 구현
- `data:*:api`: data source contract, DTO, event model contract
- `data:*:impl`: network, database, SDK 등 실제 data source 구현

**Navigation & Presentation**

- `navigator`: navigation abstraction, back stack, result 전달
- `presentation`: 공통 Compose theme, UI foundation, component
- `presentation:<feature>:api`: 외부 feature가 참조하는 최소 navigation contract
- `presentation:<feature>:impl`: 화면 UI, ViewModel, State, Intent, SideEffect, DI 등록
