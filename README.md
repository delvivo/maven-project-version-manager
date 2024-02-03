# Description

This plugin allows to increase the project version and save it to the pom.xml file

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