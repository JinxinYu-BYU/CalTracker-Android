package me.jinxinyu.caltracker.service.request;




import me.jinxinyu.caltracker.domain.Record;

import java.io.Serializable;
import java.util.List;

public class PostBatchRequest implements Serializable {

    private List<Record> records;
    private String token;
    private String alias;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    private PostBatchRequest() {}

    public PostBatchRequest(List<Record> records,  String token) {
        this.records = records;
        this.token = token;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }
}
