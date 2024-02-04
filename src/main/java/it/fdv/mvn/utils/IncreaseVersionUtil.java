package it.fdv.mvn.utils;

import it.fdv.mvn.exception.SnapshotException;
import it.fdv.mvn.singleton.ParametersValues;
import org.apache.maven.model.Model;
import org.apache.maven.model.Parent;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.*;

public class IncreaseVersionUtil {

    private IncreaseVersionUtil() {}

    public static String increaseMajorReleaseVersion(String version) throws SnapshotException {

        if (version.contains("SNAPSHOT") && !ParametersValues.instance().isForce())
            throw new SnapshotException();

        String[] v = version.split("\\.");

        int release = Integer.parseInt(v[0]);

        return String.format("%d.%d.%d", ++release, 0, 0);
    }

    public static String increaseReleaseVersion(String version) throws SnapshotException {

        if (version.contains("SNAPSHOT") && !ParametersValues.instance().isForce())
            throw new SnapshotException();

        String[] v = version.split("\\.");

        int release = Integer.parseInt(v[1]);

        return String.format("%s.%d.%d", v[0], ++release, 0);
    }

    public static String increaseSnapshotVersion(String version) {

        String[] v = version.replace("-SNAPSHOT", "").split("\\.");

        int release = Integer.parseInt(v[1]);

        return String.format("%s.%d.%d-SNAPSHOT", v[0], ++release, 0);
    }

    public static String increaseMinorVersion(String version) throws SnapshotException {

        if (version.contains("SNAPSHOT") && !ParametersValues.instance().isForce())
            throw new SnapshotException();

        String[] v = version.replace("-SNAPSHOT", "").split("\\.");

        int minor = Integer.parseInt(v[2]);

        return String.format("%s.%s.%d", v[0], v[1], ++minor);
    }

    public static void saveProjectChanges(MavenProject project, String version) throws XmlPullParserException, IOException {
        MavenXpp3Reader reader = new MavenXpp3Reader();
        Model model = reader.read(new FileReader(project.getFile()));

        if (project.getModules() != null && !project.getModules().isEmpty()) {
            saveModulesChanges(project, version);
        }

        model.setVersion(version);

        MavenXpp3Writer writer = new MavenXpp3Writer();
        Writer fileWriter = new FileWriter(project.getFile());
        writer.write(fileWriter, model);
        fileWriter.close();
    }

    private static void saveModulesChanges(MavenProject project, String newVersion) throws IOException, XmlPullParserException {
        MavenXpp3Reader reader = new MavenXpp3Reader();
        MavenXpp3Writer writer = new MavenXpp3Writer();

        Model mainProjectModel = reader.read(new FileReader(project.getFile()));

        for (String moduleName : mainProjectModel.getModules()) {
            File modulePomFile = new File(project.getBasedir(), moduleName + "/pom.xml");
            Model moduleModel = reader.read(new FileReader(modulePomFile));

            Parent parent = moduleModel.getParent();
            if (parent != null && project.getGroupId().equals(parent.getGroupId()) && project.getArtifactId().equals(parent.getArtifactId())) {
                parent.setVersion(newVersion);

                if (moduleModel.getVersion().equals(project.getVersion())) {
                    moduleModel.setVersion(newVersion);
                }

                Writer moduleWriter = new FileWriter(modulePomFile);
                writer.write(moduleWriter, moduleModel);
                moduleWriter.close();
            }
        }
    }
}
