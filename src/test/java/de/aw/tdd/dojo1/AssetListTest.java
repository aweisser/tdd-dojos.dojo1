package de.aw.tdd.dojo1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.matchers.JUnitMatchers.hasItems;

/**
 * @author armin.weisser
 */
public class AssetListTest {

    @Test
    public void emptyList() {
        assertTrue(new AssetList().isEmpty());
    }

    @Test
    public void addAssets() {
        AssetList assetList = new AssetList();
        Asset asset1 = new Asset();
        Asset asset2 = new Asset();
        assetList.add(asset1, asset2);
        assertEquals(2, assetList.size());
    }

    @Test
    public void getNames() {
        AssetList assetList = new AssetList();
        Asset asset1 = new Asset();
        asset1.setName("Asset 1");

        Asset asset2 = new Asset();
        asset2.setName("Asset 2");

        assetList.add(asset1, asset2);
        assertThat(assetList.getNames(), hasItems("Asset 1", "Asset 2"));
    }


}
