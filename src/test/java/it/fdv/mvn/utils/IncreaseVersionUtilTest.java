package it.fdv.mvn.utils;

import it.fdv.mvn.exception.SnapshotException;
import org.junit.Assert;
import org.junit.Test;

public class IncreaseVersionUtilTest {

    @Test
    public void shouldIncreaseMajorReleaseVersion() throws SnapshotException {
        String version1 = "2.3.1";
        String version2 = "0.3.0";
        String version3 = "1.9.3";

        Assert.assertEquals(IncreaseVersionUtil.increaseMajorReleaseVersion(version1), "3.0.0");
        Assert.assertEquals(IncreaseVersionUtil.increaseMajorReleaseVersion(version2), "1.0.0");
        Assert.assertEquals(IncreaseVersionUtil.increaseMajorReleaseVersion(version3), "2.0.0");
    }

    @Test
    public void shouldIncreaseReleaseVersion() throws SnapshotException {
        String version1 = "2.3.1";
        String version2 = "0.3.0";
        String version3 = "1.9.3";

        Assert.assertEquals(IncreaseVersionUtil.increaseReleaseVersion(version1), "2.4.0");
        Assert.assertEquals(IncreaseVersionUtil.increaseReleaseVersion(version2), "0.4.0");
        Assert.assertEquals(IncreaseVersionUtil.increaseReleaseVersion(version3), "1.10.0");
    }

    @Test
    public void shouldIncreaseSnapshotVersion() {
        String version1 = "2.3.0-SNAPSHOT";
        String version2 = "0.3.0-SNAPSHOT";
        String version3 = "1.9.0-SNAPSHOT";

        Assert.assertEquals(IncreaseVersionUtil.increaseSnapshotVersion(version1), "2.4.0-SNAPSHOT");
        Assert.assertEquals(IncreaseVersionUtil.increaseSnapshotVersion(version2), "0.4.0-SNAPSHOT");
        Assert.assertEquals(IncreaseVersionUtil.increaseSnapshotVersion(version3), "1.10.0-SNAPSHOT");
    }

    @Test
    public void shouldIncreaseMinorVersion() throws SnapshotException {
        String version1 = "2.3.1";
        String version2 = "0.3.0";
        String version3 = "1.9.3";

        Assert.assertEquals(IncreaseVersionUtil.increaseMinorVersion(version1), "2.3.2");
        Assert.assertEquals(IncreaseVersionUtil.increaseMinorVersion(version2), "0.3.1");
        Assert.assertEquals(IncreaseVersionUtil.increaseMinorVersion(version3), "1.9.4");
    }
}