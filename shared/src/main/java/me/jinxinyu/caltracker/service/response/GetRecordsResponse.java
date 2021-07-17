package me.jinxinyu.caltracker.service.response;

import me.jinxinyu.caltracker.domain.Record;

import java.util.List;
import java.util.Objects;

public class GetRecordsResponse extends PagedResponse {

    private List<Record> records;

    /**
     * Creates a response indicating that the corresponding request was unsuccessful. Sets the
     * success and more pages indicators to false.
     *
     * @param message a message describing why the request was unsuccessful.
     */
    public GetRecordsResponse(boolean success, String message, boolean hasMorePages, List<Record>records) {
        super(success, message, hasMorePages);
        this.records = records;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public GetRecordsResponse(GetRecordsResponse getRecordsResponse, String token) {
        super(new Response(getRecordsResponse.getSuccess(), getRecordsResponse.getMessage()), token, getRecordsResponse.getHasMorePages());
        this.records = getRecordsResponse.getRecords();

    }



    @Override
    public boolean equals(Object param) {
        if (this == param) {
            return true;
        }

        if (param == null || getClass() != param.getClass()) {
            return false;
        }

        GetRecordsResponse that = (GetRecordsResponse) param;

        return (Objects.equals(records, that.records) &&
                Objects.equals(this.getMessage(), that.getMessage()) &&
                this.getSuccess() == that.getSuccess());
    }

    @Override
    public int hashCode() {
        return Objects.hash(records);
    }
}
