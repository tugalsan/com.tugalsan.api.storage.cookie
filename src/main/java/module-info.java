module com.tugalsan.api.storage.cookie {
    requires javax.servlet.api;
    requires com.tugalsan.api.callable;
    requires com.tugalsan.api.thread;
    requires com.tugalsan.api.time;
    requires com.tugalsan.api.log;
    requires com.tugalsan.api.stream;
    requires com.tugalsan.api.cast;
    requires com.tugalsan.api.list;
    exports com.tugalsan.api.storage.cookie.client;
    exports com.tugalsan.api.storage.cookie.server;
}
