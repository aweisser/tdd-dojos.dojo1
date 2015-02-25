package de.aw.tdd.dojo1;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.hasItems;

/**
 * @author armin.weisser
 */
public class AssetTest {

    @Test
    public void createAssetFromMap() {
        Map<String, Object> fieldMap = new HashMap<String, Object>();
        fieldMap.put("id", "1");
        fieldMap.put("name", "My Asset");

        Asset asset = Asset.fromMap(fieldMap);
        assertEquals("1", asset.getId());
        assertEquals("My Asset", asset.getName());
    }


    @Test
    public void createAssetFromEmptyMap() {
        Map<String, Object> fieldMap = new HashMap<String, Object>();

        Asset asset = Asset.fromMap(fieldMap);
        assertNull(asset.getId());
        assertNull(asset.getName());

        assertThat(asset, equalTo(new Asset()));
    }

    @Test
    public void createAssetFromMapNull() {
        Map<String, Object> fieldMap = null;

        Asset asset = Asset.fromMap(fieldMap);
        assertNull(asset.getId());
        assertNull(asset.getName());

        assertThat(asset, equalTo(new Asset()));
    }

    @Test
    public void equalsOnlyId_goodCase() {
        Asset asset1 = new Asset();
        asset1.setId("1");

        Asset asset2 = new Asset();
        asset2.setId("1");

        assertThat(asset1, equalTo(asset2));
    }

    @Test
    public void equalsOnlyId_badCase() {
        Asset asset1 = new Asset();
        asset1.setId("1");

        Asset asset2 = new Asset();
        asset2.setId("2");

        assertThat(asset1, not(equalTo(asset2)));
    }

    @Test
    public void equalsAllFields() {
        Asset asset1 = new Asset();
        asset1.setId("1");
        asset1.setName("My Asset");

        Asset asset2 = new Asset();
        asset2.setId("1");
        asset2.setName("My Asset");

        assertThat(asset1, equalTo(asset2));
    }

    @Test
    public void createAssetFromFileNull() {
        Asset asset = Asset.fromFile(null);
        assertThat(asset, equalTo(new Asset()));
    }

    @Test
    public void createAssetFromFileNotExists() {
        Asset asset = Asset.fromFile(new File("invalid"));
        assertThat(asset, equalTo(new Asset()));
    }

    @Test
    public void createAssetFromFile() throws URISyntaxException {
        Asset asset = Asset.fromFile(new File(getClass().getResource("/assets/7.jpg").toURI()));
        assertEquals("7.jpg", asset.getName());
    }

    @Test
    public void createAssetFromRelativePathInClasspath() throws URISyntaxException {
        Asset asset = Asset.fromPath("/assets/7.jpg");
        assertEquals("7.jpg", asset.getName());
    }

    @Test
    public void createAssetFromRelativePath() throws URISyntaxException, IOException {
        File file = new File("test.file");
        file.createNewFile();
        file.deleteOnExit();

        assertTrue(file.exists());

        Asset asset = Asset.fromPath("test.file");
        assertEquals("test.file", asset.getName());
    }

    @Test
    public void createAssetFromAbsolutePath() throws URISyntaxException, IOException {
        File file = new File("test.file");
        file.createNewFile();
        file.deleteOnExit();

        assertTrue(file.exists());

        Asset asset = Asset.fromPath(file.getCanonicalPath());
        assertEquals("test.file", asset.getName());
    }

}
