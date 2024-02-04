package it.fdv.mvn;

import it.fdv.mvn.utils.IncreaseVersionUtil;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.IOException;

@Mojo(name = "increase-snapshot")
public class IncreaseSnapshotVersionMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    @Override
    public void execute() {
        try {
            String projectVersion = project.getVersion();
            String newVersion = IncreaseVersionUtil.increaseSnapshotVersion(projectVersion);
            getLog().info(String.format("Actual version: %s", projectVersion));
            if (project.isExecutionRoot()) {
                IncreaseVersionUtil.saveProjectChanges(project, newVersion);
            }
            getLog().info(String.format("New version: %s", newVersion));
        }
        catch (IOException | XmlPullParserException e) {
            getLog().error(String.format("Error saving pom.xml file - %s", e.getMessage()));
        }
    }

}
