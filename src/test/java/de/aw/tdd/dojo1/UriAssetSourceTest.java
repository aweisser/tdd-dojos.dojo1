package de.aw.tdd.dojo1;

import org.junit.Before;
import org.junit.Test;
import sun.misc.Launcher;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItems;

/**
 * @author armin.weisser
 */
public class UriAssetSourceTest {

    public static URI TEST_ASSETS_URI;

    static {
        try {
            TEST_ASSETS_URI = Launcher.class.getResource("/assets").toURI();
        } catch (URISyntaxException e) {
            assertNull("Test assets are missing");
        }
    }

    private AssetSource assetSource;

    @Before
    public void setup() throws URISyntaxException {
        assetSource = new UriAssetSource(TEST_ASSETS_URI);
    }

    @Test
    public void listAssets() throws URISyntaxException {
        AssetList assetList = assetSource.listAssets();
        Collection<String> assetNames = new ArrayList<String>(assetList.size());
        for(Asset asset: assetList) {
            assetNames.add(asset.getName());
        }
        assertThat(assetNames, hasItems("7.jpg", "Dragon001.jpg"));
    }


}
