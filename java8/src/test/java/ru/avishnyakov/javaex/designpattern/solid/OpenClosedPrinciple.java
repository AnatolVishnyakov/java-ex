package ru.avishnyakov.javaex.designpattern.solid;

public class OpenClosedPrinciple {
    /*
        interface MetricDataGraph {
            void updateUserTime(int value);

            void updateSystemTime(int value);

            void updateIoTime(int value);
        }
    */

    interface MetricDataGraph {
        void addTimeSeries(TimeSeries values);
    }

    interface TimeSeries {
    }

    class UserTimeSeries implements TimeSeries {
    }

    class SystemTimeSeries implements TimeSeries {
    }

    class IoTimeSeries implements TimeSeries {
    }

    class StealTimeSeries implements TimeSeries {
    }
}
