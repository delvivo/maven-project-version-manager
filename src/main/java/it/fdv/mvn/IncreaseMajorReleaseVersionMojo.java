package it.fdv.mvn;

import it.fdv.mvn.exception.NoBooleanValueException;
import it.fdv.mvn.exception.SnapshotException;
import it.fdv.mvn.singleton.ParametersValues;
import it.fdv.mvn.utils.IncreaseVersionUtil;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.IOException;

@Mojo(name = "increase-major")
public class IncreaseMajorReleaseVersionMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    @Parameter(property = "force", defaultValue = "false")
    private String force;

    @Override
    public void execute() {
        try {
            ParametersValues.instance().setForce(force);
            String projectVersion = project.getVersion();
            String newVersion = IncreaseVersionUtil.increaseMajorReleaseVersion(projectVersion);
            getLog().info(String.format("Actual version: %s", projectVersion));
            if (project.isExecutionRoot()) {
                IncreaseVersionUtil.saveProjectChanges(project, newVersion);
            }
            getLog().info(String.format("New version: %s", newVersion));
        }
        catch (IOException | XmlPullParserException e) {
            getLog().error(String.format("Error saving pom.xml file - %s", e.getMessage()));
        }
        catch (NoBooleanValueException | SnapshotException e) {
            getLog().error(e.getMessage());
        }
    }

}
