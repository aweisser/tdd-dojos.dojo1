package de.aw.tdd.dojo1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author armin.weisser
 */
public class AssetList implements Iterable<Asset> {

    Collection<Asset> delegate;

    public AssetList() {
        this(new ArrayList());
    }

    public AssetList(Collection<Asset> assetCollection) {
        this.delegate = assetCollection;
    }

    public boolean isEmpty() {
        return this.delegate.isEmpty();
    }

    public void add(Asset... assets) {
        this.delegate.addAll(Arrays.asList(assets));
    }

    public int size() {
        return this.delegate.size();
    }

    @Override
    public Iterator<Asset> iterator() {
        return delegate.iterator();
    }

    public Collection<String> getNames() {
        Collection<String> names = new ArrayList<String>(size());
        for(Asset asset: this) {
            names.add(asset.getName());
        }
        return names;
    }
}
