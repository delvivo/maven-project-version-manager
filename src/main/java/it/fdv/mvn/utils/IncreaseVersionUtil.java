package it.fdv.mvn.utils;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class IncreaseVersionUtil {

    private IncreaseVersionUtil() {}

    public static String increaseMajorReleaseVersion(String version) {

        String[] v = version.split("\\.");

        int release = Integer.parseInt(v[0]);

        return String.format("%d.%d.%d", ++release, 0, 0);
    }

    public static String increaseReleaseVersion(String version) {

        String[] v = version.split("\\.");

        int release = Integer.parseInt(v[1]);

        return String.format("%s.%d.%d", v[0], ++release, 0);
    }

    public static String increaseMajorSnapshotVersion(String version) {

        String[] v = version.replace("-SNAPSHOT", "").split("\\.");

        int release = Integer.parseInt(v[0]);

        return String.format("%s.%d.%d-SNAPSHOT", ++release, 0, 0);
    }

    public static String increaseSnapshotVersion(String version) {

        String[] v = version.replace("-SNAPSHOT", "").split("\\.");

        int release = Integer.parseInt(v[1]);

        return String.format("%s.%d.%d-SNAPSHOT", v[0], ++release, 0);
    }

    public static String increaseMinorVersion(String version) {

        String[] v = version.split("\\.");

        int minor = Integer.parseInt(v[2]);

        return String.format("%s.%s.%d", v[0], v[1], ++minor);
    }

    public static void saveProjectChanges(MavenProject project, String version) throws XmlPullParserException, IOException {
        MavenXpp3Reader reader = new MavenXpp3Reader();
        Model model = reader.read(new FileReader(project.getFile()));

        model.setVersion(version);

        MavenXpp3Writer writer = new MavenXpp3Writer();
        Writer fileWriter = new FileWriter(project.getFile());
        writer.write(fileWriter, model);
        fileWriter.close();
    }
}
