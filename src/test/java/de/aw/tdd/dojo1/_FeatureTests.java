package de.aw.tdd.dojo1;

import com.github.tlrx.elasticsearch.test.annotations.ElasticsearchClient;
import com.github.tlrx.elasticsearch.test.annotations.ElasticsearchIndex;
import com.github.tlrx.elasticsearch.test.annotations.ElasticsearchNode;
import com.github.tlrx.elasticsearch.test.support.junit.runners.ElasticsearchRunner;
import de.aw.tdd.dojo1.*;
import org.elasticsearch.client.Client;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.net.URI;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author armin.weisser
 */
@RunWith(ElasticsearchRunner.class)
@ElasticsearchNode
public class _FeatureTests {

    @ElasticsearchClient
    Client client;

    @Test
    @ElasticsearchIndex(indexName = "story1")
    public void story1_createIntialIndexFromExistingImageFolder() throws IOException {
        URI test_assets_uri = UriAssetSourceTest.TEST_ASSETS_URI;
        AssetSource assetSource = new UriAssetSource(test_assets_uri);
        AssetSearchIndex assetSearchIndex = new EsAssetSearchIndex(client, "story1");

        Asset[] assetList = assetSource.listAssets();
        assetSearchIndex.update(assetList);

        assertThat((int)assetSearchIndex.count(), equalTo(assetList.length));
    }

}
