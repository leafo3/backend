package com.spaceapps.model;

import com.sun.jersey.core.header.FormDataContentDisposition;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.io.InputStream;

/**
 * Created by Alberto Rubalcaba on 4/11/2015.
 */
public class LeafInfoFileContent {

    private InputStream leafStream;

    private FormDataContentDisposition leafDisposition;

    public LeafInfoFileContent(InputStream leafStream) {
        this.leafStream = leafStream;
    }

    public LeafInfoFileContent() {
    }

    @JsonIgnore
    public InputStream getLeafStream() {
        return leafStream;
    }

    @JsonIgnore
    public void setLeafStream(InputStream leafStream) {
        this.leafStream = leafStream;
    }

    @JsonIgnore
    public FormDataContentDisposition getLeafDisposition() {
        return leafDisposition;
    }

    @JsonIgnore
    public void setLeafDisposition(FormDataContentDisposition leafDisposition) {
        this.leafDisposition = leafDisposition;
    }
}
