package io.github.xesam.web.bridge.core;

public final class Protocol {

    public static final String VERSION = "0.0.1";

    public interface Native {

        String NATIVE_BRIDGE_NAME = "_NATIVE_BRIDGE";
        String NATIVE_BRIDGE_VERSION = "_version";

        int TYPE_CALL = 0;
        int TYPE_CALLBACK = 1;

        String ACTION_TYPE = "type";
        String ACTION_NAME = "name";

        String ERROR_STATUS = "status";
        String ERROR_MESSAGE = "message";

        String RESPONSE_ERROR = "err";
        String RESPONSE_DATA = "data";
    }

    public interface Web {
        String WEB_BRIDGE_NAME = "_WEB_BRIDGE";
        String WEB_BRIDGE_VERSION = "_version";

        int TYPE_CALL = Native.TYPE_CALL;
        int TYPE_CALLBACK = Native.TYPE_CALLBACK;

        String ACTION_TYPE = Native.ACTION_TYPE;
        String ACTION_NAME = Native.ACTION_NAME;

        String ERROR_STATUS = Native.ERROR_STATUS;
        String ERROR_MESSAGE = Native.ERROR_MESSAGE;

        String RESPONSE_ERROR = Native.RESPONSE_ERROR;
        String RESPONSE_DATA = Native.RESPONSE_DATA;
    }
}
