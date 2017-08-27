# musicbrainzws2-java

[![Build Status](https://travis-ci.org/schnatterer/musicbrainzws2-java.svg?branch=master)](https://travis-ci.org/schnatterer/musicbrainzws2-java)
[![License](https://img.shields.io/github/license/schnatterer/musicbrainzws2-java.svg)](LICENSE)

This is a fork of musicbrainzws2-java, originally hosted [here](https://code.google.com/archive/p/musicbrainzws2-java/). It includes several bugfixes as well as maven integration. The original [wiki](https://code.google.com/p/musicbrainzws2-java/w/list) was migrated and can now be found [here](https://github.com/schnatterer/musicbrainzws2-java/wiki).
You can find the original Readme file [here](README.txt).

This fork was implemented as part of the [nusic-app](https://github.com/schnatterer/nusic).

## How to use
For now, this is not hosted on maven central, but on this very repository. To use it, you have two options:

1. Add this repo as maven repository
2. Use jitPack

See the [wiki](https://github.com/schnatterer/musicbrainzws2-java/wiki) for further information.

### Add this repo as maven repository
Add the following maven repository to your POM.xml

    <repositories>
        <repository>
            <id>musicbrainzws2-java-mvn-repo</id>
            <url>https://raw.github.com/schnatterer/musicbrainzws2-java/mvn-repo/</url>
        </repository>
    </repositories>
Then add the actual dependency

        <dependency>
            <groupId>info.schnatterer.musicbrainzws2-java</groupId>
            <artifactId>musicbrainzws2-java</artifactId>
            <version>3.0.0</version>
        </dependency>

### Use jitPack
Add the following maven repository to your POM.xml

    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>
Then add the actual dependency

        <dependency>
            <groupId>com.github.schnatterer</groupId>
            <artifactId>musicbrainzws2-java</artifactId>
            <version>v.3.0.0</version>
        </dependency>
