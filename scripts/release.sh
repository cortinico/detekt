#!/usr/bin/env sh
set -e
gradle publishToMavenLocal
gradle build
gradle publishToSonatype closeSonatypeStagingRepository
gradle :detekt-gradle-plugin:publishPlugins
gradle githubRelease
gradle applyDocVersion
gradle releaseSonatypeStagingRepository
