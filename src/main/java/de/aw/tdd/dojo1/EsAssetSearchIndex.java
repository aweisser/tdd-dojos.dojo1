package de.aw.tdd.dojo1;


import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.count.CountResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.search.SearchHit;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;
import static org.elasticsearch.index.query.QueryBuilders.inQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

/**
 * @author armin.weisser
 */
public class EsAssetSearchIndex implements AssetSearchIndex {

    private final Client client;
    private final String indexName;
    private final String documentType;

    public EsAssetSearchIndex(Client client, String indexName) {
        this.client = client;
        this.indexName = indexName;
        this.documentType = Asset.class.getSimpleName();
    }


    @Override
    public boolean update(Asset... assets) throws IOException {
        AssetList assetList = new AssetList();
        assetList.add(assets);
        return update(assetList);
    }

    @Override
    public boolean update(AssetList assetList) throws IOException {
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        for(Asset asset: assetList) {
            bulkRequest.add(getIndexRequestBuilder(asset));
        }

        BulkResponse bulkResponse = bulkRequest.execute().actionGet();

        // After submitting a document to an in-memory node, we need to refresh the index:
        client.admin().indices().prepareRefresh().execute().actionGet();

        return !bulkResponse.hasFailures();

    }

    private IndexRequestBuilder getIndexRequestBuilder(Asset asset) throws IOException {
        String id = asset.getId();
        if(StringUtils.isEmpty(id)) {
            id = UUID.randomUUID().toString();
        }
        XContentBuilder assetJson = jsonBuilder()
                .startObject()
                .field("lastUpdated", new Date())
                .field("id", id)
                .field("name", asset.getName())
                .endObject();

        return client.prepareIndex(indexName, documentType, asset.getId())
                .setSource(assetJson);
    }

    /**
     *
     * @return number of all Assets in the index.
     */
    @Override
    public long count() {
        CountResponse countResponse = client.prepareCount(indexName)
                .setQuery(termQuery("_type", documentType))
                .execute()
                .actionGet();

        return countResponse.getCount();
    }

    @Override
    public AssetList listAll() {
        SearchResponse searchResponse = client.prepareSearch(indexName)
                .setTypes(documentType)
                .setSearchType(SearchType.QUERY_AND_FETCH)
                .execute()
                .actionGet();

        return createAssetList(searchResponse);
    }

    private AssetList createAssetList(SearchResponse searchResponse) {
        AssetList assetList = new AssetList();
        for(SearchHit hit: searchResponse.getHits()) {
            assetList.add(Asset.fromMap(hit.getSource()));
        }

        return assetList;
    }
}
