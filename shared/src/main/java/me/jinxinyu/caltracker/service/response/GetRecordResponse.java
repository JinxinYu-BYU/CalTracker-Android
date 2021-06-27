package me.jinxinyu.caltracker.service.response;

import me.jinxinyu.caltracker.domain.Record;

import java.util.List;
import java.util.Objects;

public class GetRecordResponse extends PagedResponse {

    private List<Record> records;

    /**
     * Creates a response indicating that the corresponding request was unsuccessful. Sets the
     * success and more pages indicators to false.
     *
     * @param message a message describing why the request was unsuccessful.
     */
    public GetRecordResponse(String message) {
        super(false, message, false);
    }

    /**
     * Creates a response indicating that the corresponding request was successful.
     *
     * @param records the statuses to be included in the result.
     * @param hasMorePages an indicator of whether more data is available for the request.
     */
    public GetRecordResponse(List<Record> records, boolean hasMorePages) {
        super(true, "Succssfully get records!", hasMorePages);
        this.records = records;
    }



    @Override
    public boolean equals(Object param) {
        if (this == param) {
            return true;
        }

        if (param == null || getClass() != param.getClass()) {
            return false;
        }

        GetRecordResponse that = (GetRecordResponse) param;

        return (Objects.equals(records, that.records) &&
                Objects.equals(this.getMessage(), that.getMessage()) &&
                this.isSuccess() == that.isSuccess());
    }

    @Override
    public int hashCode() {
        return Objects.hash(records);
    }
}
