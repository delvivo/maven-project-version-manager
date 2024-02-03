package it.fdv.mvn.singleton;

public class ParentProperties {

    private static ParentProperties properties;

    private String artifactId;

    private String currentVersion;

    private ParentProperties() {}

    public static ParentProperties instance() {

        if (properties == null) {
            properties = new ParentProperties();
        }

        return properties;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(String currentVersion) {
        this.currentVersion = currentVersion;
    }
}
