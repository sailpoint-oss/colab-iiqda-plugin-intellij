package com.colab.iiqdaintellij.utils;

import com.intellij.openapi.util.registry.Registry;
import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class WorkflowArgsPayload {

    private static final boolean DEBUG_PAYLOAD = Registry.is(IIQDAConstants.PLUGIN_ID + "/debug/WorkflowArgsPayload", true);

    private Map<String, String> args;

    public WorkflowArgsPayload() {
        this.args = new HashMap<String, String>();
    }

    public WorkflowArgsPayload(String key, String value) {
        this();
        add(key, value);
    }

    public WorkflowArgsPayload(Map<String, String> args) {
        this();
        this.args = args;
    }

    public StringEntity getEntity() throws UnsupportedEncodingException {
        StringBuilder builder = new StringBuilder("{\n");

        builder.append("  \"workflowArgs\" : {");
        boolean first = true;
        for (String key : args.keySet()) {
            if (first) {
                first = false;
            } else {
                builder.append(",");
            }
            builder.append("\n    \"");
            builder.append(key);
            builder.append("\" : \"");
            builder.append(jsonified(args.get(key)));
            builder.append("\"");
        }
        builder.append("\n  }\n");
        builder.append("}\n");
        StringEntity entity = new StringEntity(builder.toString());
        entity.setContentType("application/json");
        return entity;
    }

    private String jsonified(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {
            if ((c == '\\') || (c == '\"')) {
                sb.append('\\');
                sb.append(c);
            } else if (c == '\b')
                sb.append("\\b");
            else if (c == '\t')
                sb.append("\\t");
            else if (c == '\n')
                sb.append("\\n");
            else if (c == '\f')
                sb.append("\\f");
            else if (c == '\r')
                sb.append("\\r");

            else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public void add(String key, String value) {
        args.put(key, value);
    }
}


