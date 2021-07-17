package me.jinxinyu.caltracker.service.response;

import me.jinxinyu.caltracker.domain.Record;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ClearCartResponse extends PagedResponse {

        private List<Record> records = new LinkedList<>();



        public ClearCartResponse(ClearCartResponse clearCartResponse, String token){
            super(new Response(true, "successfully get clear cart"), token, clearCartResponse.getHasMorePages());
            this.records = clearCartResponse.getRecordss();

        }
        public ClearCartResponse(boolean success, String message) {
            super(success, message, false);
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
                    this.getSuccess() == that.getSuccess());
        }

        @Override
        public int hashCode() {
            return Objects.hash(records);
        }

}
