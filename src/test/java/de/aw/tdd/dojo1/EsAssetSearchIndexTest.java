package de.aw.tdd.dojo1;

import com.github.tlrx.elasticsearch.test.annotations.ElasticsearchClient;
import com.github.tlrx.elasticsearch.test.annotations.ElasticsearchIndex;
import com.github.tlrx.elasticsearch.test.annotations.ElasticsearchNode;
import com.github.tlrx.elasticsearch.test.support.junit.runners.ElasticsearchRunner;
import org.elasticsearch.client.Client;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.matchers.JUnitMatchers.hasItems;

/**
 * @author armin.weisser
 */
@RunWith(ElasticsearchRunner.class)
@ElasticsearchNode
public class EsAssetSearchIndexTest {

    @ElasticsearchClient
    Client client;

    @Test
    @ElasticsearchIndex(indexName = "library4")
    public void bulkInsert() throws IOException {
        AssetSearchIndex assetSearchIndex = new EsAssetSearchIndex(client, "library4");

        Asset asset1 = new Asset();
        asset1.setId("1");

        Asset asset2 = new Asset();
        asset2.setId("2");

        Asset asset3 = new Asset();
        asset3.setId("3");

        assertTrue(assetSearchIndex.update(asset1, asset2, asset3));
        assertEquals(3L, assetSearchIndex.count());
    }


}
