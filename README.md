# musicbrainzws2-java

[![Build Status](https://travis-ci.org/schnatterer/musicbrainzws2-java.svg?branch=nusicFork)](https://travis-ci.org/schnatterer/musicbrainzws2-java)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=info.schnatterer.musicbrainzws2-java%3Amusicbrainzws2-java&metric=alert_status)](https://sonarcloud.io/dashboard?id=info.schnatterer.musicbrainzws2-java%3Amusicbrainzws2-java)

This is a fork of musicbrainzws2-java, originally hosted [here](https://code.google.com/archive/p/musicbrainzws2-java/). It includes several bugfixes as well as maven integration. The original [wiki](https://code.google.com/p/musicbrainzws2-java/w/list) was migrated and can now be found [here](https://github.com/schnatterer/musicbrainzws2-java/wiki).
You can find the original Readme file [here](README.txt).

This fork was implemented as part of the [nusic-app](https://github.com/schnatterer/nusic).



## How to use

Add the [latest stable version of musicbrainzws2-java](https://search.maven.org/search?q=a:musicbrainzws2-java%20AND%20g:info.schnatterer.musicbrainzws2-java) 
to the dependency management tool of your choice.

```XML
<dependency>
    <groupId>info.schnatterer.musicbrainzws2-java</groupId>
    <artifactId>musicbrainzws2-java</artifactId>
    <version>3.0.2</version>
</dependency>
```

[![Maven Central](https://img.shields.io/maven-central/v/info.schnatterer.musicbrainzws2-java/musicbrainzws2-java.svg)](https://search.maven.org/search?q=a:musicbrainzws2-java%20AND%20g:info.schnatterer.musicbrainzws2-java)

You can also get snapshot versions from our [snapshot repository](https://oss.sonatype.org/content/repositories/snapshots/info/schnatterer/musicbrainzws2-java/) 
(for the most recent commit).
To do so, add the following repo to your `pom.xml` or `settings.xml`:

```xml
<repository>
    <id>snapshots-repo</id>
    <url>https://oss.sonatype.org/content/repositories/snapshots</url>
    <releases><enabled>false</enabled></releases>
    <snapshots><enabled>true</enabled></snapshots>
</repository>
```

Older versions were released in a different repo. See [Git history](https://github.com/schnatterer/musicbrainzws2-java/blob/b9bf6d7f8be8df66dfd3d85f7ab6f569475d7c2f/README.md) 
for details on how to use them.

## Releasing

```shell script
./mvnw release:prepare -Darguments=pgp.skip=true
```

Sets versions in pom.xml, commits, tags and pushes to SCM. Travis builds tag and pushes to Maven Central. 
