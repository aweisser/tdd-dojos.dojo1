package de.aw.tdd.dojo1;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.File;
import java.net.URISyntaxException;
import java.util.*;

/**
 * @author armin.weisser
 */
public class Asset {

    private String id;
    private String name;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Asset)) return false;

        Asset asset = (Asset) o;

        if (id != null ? !id.equals(asset.id) : asset.id != null) return false;
        if (name != null ? !name.equals(asset.name) : asset.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(91, 9)
                .append(id)
                .append(name)
                .toHashCode();
    }


    public static Asset fromPath(String path) {
        File file = new File(path);
        if(!file.exists()) {
            try {
                file = new File(Asset.class.getResource(path).toURI());
            } catch (URISyntaxException e) {
                // nothing. An empty Asset will be created.
            }
        }
        return fromFile(file);
    }

    public static Asset fromFile(File file) {
        Asset asset = new Asset();
        if(file != null && file.exists()) {
            asset.setName(file.getName());
        }
        return asset;
    }

    public static Asset fromMap(Map<String, Object> fieldMap) {
        Asset asset = new Asset();
        if(MapUtils.isNotEmpty(fieldMap)) {
            asset.setId(getValueOrNull(fieldMap.get("id"), String.class));
            asset.setName(getValueOrNull(fieldMap.get("name"), String.class));
        }
        return asset;
    }

    private static <T> T getValueOrNull(Object value, Class<T> clazz) {
        if(value == null) {
            return null;
        }
        return (T)value;
    }

}
