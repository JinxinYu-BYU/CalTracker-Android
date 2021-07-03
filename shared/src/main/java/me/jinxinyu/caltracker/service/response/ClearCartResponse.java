package me.jinxinyu.caltracker.service.response;

import me.jinxinyu.caltracker.domain.Record;

import java.util.List;
import java.util.Objects;

public class ClearCartResponse extends PagedResponse {

        private List<Record> records;


        public ClearCartResponse(String message) {
            super(false, message, false);
        }

        public ClearCartResponse(List<Record> records, boolean hasMorePages) {
            super(true, "success", hasMorePages);
            this.records = records;
        }

        public List<Record> getRecordss() {
            return records;
        }

        @Override
        public boolean equals(Object param) {
            if (this == param) {
                return true;
            }

            if (param == null || getClass() != param.getClass()) {
                return false;
            }

            ClearCartResponse that = (ClearCartResponse) param;

            return (Objects.equals(records, that.records) &&
                    Objects.equals(this.getMessage(), that.getMessage()) &&
                    this.isSuccess() == that.isSuccess());
        }

        @Override
        public int hashCode() {
            return Objects.hash(records);
        }

}
