# Description

This plugin allows to increase the project version and save it to the pom.xml file

Add this plugin as below:

```
<plugin>
    <groupId>it.fdv.mvn</groupId>
    <artifactId>maven-project-version-manager</artifactId>
    <version>1.0.0</version>
</plugin>
```

### Increase Release version

To increase the release version, type the following command:

```
mvn maven-project-version-manager:increase-release
```

This command increases the release version, for example:

_1.2.0 &#8594; 1.3.0_ 


### Increase Major Release version

To increase the major release version, type the following command:

```
mvn maven-project-version-manager:increase-major
```

This command increases the major release version, for example:

_1.2.0 &#8594; 2.0.0_ 


### Increase Minor version

To increase the minor version, type the following command:

```
mvn maven-project-version-manager:increase-minor
```

This command increases the minor version, for example:

_1.2.0 &#8594; 2.2.1_ 


### Increase Snapshot version

To increase the shapshot version, type the following command:

```
mvn maven-project-version-manager:increase-snapshot
```

This command increases the snapshot version, for example:

_1.2.0 &#8594; 2.3.0-SNAPSHOT_ 


### Force Version Update from a Snapshot

It's not possible to increase a release or minor version from a Snapshot by default.

To force the update, it's possible to use the parameter _-Dforce_

For example:
```
mvn maven-project-version-manager:increase-minor -Dforce=true
```

_1.2.0-SNAPSHOT &#8594; 2.2.1_ 