package de.aw.tdd.dojo1;

import java.io.IOException;

/**
 * @author armin.weisser
 */
public interface AssetSearchIndex {

    /**
     * Create or update multiple Assets
     * @param assets
     * @return false, if the bulk update has failures.
     * @throws java.io.IOException
     */
    boolean update(Asset... assets) throws IOException;

    /**
     * Create or update multiple Assets
     * @param assetList
     * @return false, if the bulk update has failures.
     * @throws java.io.IOException
     */
    boolean update(AssetList assetList) throws IOException;


    /**
     * @return number of all available Assets.
     */
    long count();

    /**
     * @return all indexed assets
     */
    AssetList listAll();
}
