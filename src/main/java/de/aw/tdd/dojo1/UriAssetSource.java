package de.aw.tdd.dojo1;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author armin.weisser
 */
public class UriAssetSource implements AssetSource {
    private final URI assetsUri;

    public UriAssetSource(URI assetsUri) {
        this.assetsUri = assetsUri;
    }

    private Collection<File> listSourceFiles() {
        return Arrays.asList(new File(assetsUri).listFiles());
    }

    @Override
    public AssetList listAssets() {
        Collection<File> sourceFiles = listSourceFiles();
        AssetList assetList = new AssetList();
        for(File sourceFile: sourceFiles) {
            Asset asset = Asset.fromFile(sourceFile);
            assetList.add(asset);
        }
        return assetList;
    }
}
